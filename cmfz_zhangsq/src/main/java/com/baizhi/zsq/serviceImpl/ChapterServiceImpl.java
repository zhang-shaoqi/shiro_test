package com.baizhi.zsq.serviceImpl;

import ch.qos.logback.core.util.FileUtil;
import com.baizhi.zsq.dao.ChapterDAO;
import com.baizhi.zsq.entity.Album;
import com.baizhi.zsq.entity.Chapter;
import com.baizhi.zsq.service.ChapterService;
import com.baizhi.zsq.util.UUIDUtil;
import com.baizhi.zsq.util.UploadPictureUtil;
import org.apache.commons.io.FileUtils;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service    //创建service简单对象
@Transactional  //添加默认事务
public class ChapterServiceImpl implements ChapterService {

    @Resource
    private ChapterDAO chapterDAO;

    /**
     * 根据专辑id获取分页的音频
     *
     * @param page  获取音频页面
     * @param rows  获取音频展示条数
     * @param id    获取专辑id
     * @return  返回查询出的数据
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW,readOnly = true)
    public HashMap<String, Object> selectChapterByPage(Integer page, Integer rows,String id) {
        HashMap<String, Object> map = new HashMap<>();
        //当前页号  page
        map.put("page",page);
        //总条数  records
        Integer records = chapterDAO.countChapter(id);
        map.put("records",records);
        //总页数   total
        Integer total = records%rows==0?records/rows:records/rows+1;
        map.put("total",total);
        //当前页的所有数据  rows
        List<Chapter> chapterList = chapterDAO.selectChapter((page - 1) * rows, rows,id);
        map.put("rows",chapterList);
        return map;
    }

    /**
     * 添加音频
     *
     * @param chapter
     * @return
     */
    @Override
    public String add(Chapter chapter) {
        chapter.setUploadTime(new Date());
        chapter.setId(UUIDUtil.getUUID());
        chapterDAO.insertChapter(chapter);
        return chapter.getId();
    }

    /**
     * 修改音频
     *
     * @param chapter
     * @return
     */
    @Override
    public String oper(Chapter chapter) {
        chapterDAO.updateChapter(chapter);
        return chapter.getId();
    }

    /**
     * 删除音频
     *
     * @param chapter
     * @return
     */
    @Override
    public String del(Chapter chapter) {
        chapterDAO.deleteChapter(chapter);
        return chapter.getId();
    }

    /**
     * 音频文件上传
     *
     * @param id    文件上传地id
     * @param file   上传的文件
     * @param session   session作用域
     */
    @Override
    public void chapterUpload(String id, MultipartFile file, HttpSession session) {
        String realPath = session.getServletContext().getRealPath("/album/chapter");

        //调用上传工具类
        String name = UploadPictureUtil.upload(file, session, "/album/chapter");
        //获取文件大小
        double urlSize = (double) file.getSize();
        double db = urlSize / 1024 / 1024;
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String size = decimalFormat.format(db) + "MB";


        String duration = null;
        try {
            //获取音频时长
            AudioFile audioFile = AudioFileIO.read(new File(realPath,name));
            int length = audioFile.getAudioHeader().getTrackLength();
            duration = length/60+"分"+length%60+"秒";
            System.out.println("duration音频时长====="+duration);
        }catch (Exception e){
            System.out.println("获取音频时长错误");
            e.printStackTrace();
        }

        Chapter chapter = new Chapter();
        chapter.setId(id);
        chapter.setSrc(name);
        chapter.setSize(size);
        chapter.setDuration(duration);
        chapterDAO.updateChapter(chapter);
    }


    /**
     * 音频下载 和 在线播放
     *
     * @param operation     音频操作  下载或播放
     * @param src       音频信息
     */
    @Override
    public ResponseEntity<byte[]> chapterOperation(String operation, String src, HttpSession session) {
        try {
            //根据相对路径获取绝对路径
            String realPath = session.getServletContext().getRealPath("/album/chapter");
            //通过路径获取目标文件
            File file = new File(realPath, src);
            //将文件转成字节数组
            byte[] bytes = FileUtils.readFileToByteArray(file);
            //创建请求头
            HttpHeaders httpHeaders = new HttpHeaders();
            //解决下载文件乱码问题
            String s = new String(src.getBytes("utf-8"), "ISO-8859-1");
            //指定文件操作方式          下载或播放
            httpHeaders.setContentDispositionFormData(operation,s);
            //以二进制流的形式传输
            httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            return new ResponseEntity<byte[]>(bytes,httpHeaders, HttpStatus.CREATED);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}

package com.baizhi.zsq.serviceImpl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.zsq.dao.UserMapper;
import com.baizhi.zsq.entity.User;
import com.baizhi.zsq.entity.UserDistribution;
import com.baizhi.zsq.entity.UserExample;
import com.baizhi.zsq.service.UserService;
import com.baizhi.zsq.util.UUIDUtil;
import com.baizhi.zsq.util.UploadPictureUtil;
import org.apache.ibatis.session.RowBounds;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {


    @Resource
    private UserMapper userMapper;


    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public HashMap<String, Object> selectUserByPage(Integer page, Integer rows) {
        HashMap<String, Object> map = new HashMap<>();

        //总条数
        Integer records = userMapper.selectCount(null);
        map.put("records",records);
        //总页数
        Integer total = records%rows==0?records/rows:records/rows+1;
        map.put("total",total);
        //当前页
        map.put("page",page);
        //获取每页展示的数据
        List<User> userList = userMapper.selectByExampleAndRowBounds(null, new RowBounds((page-1)*rows, rows));
        map.put("rows",userList);

        return map;
    }

    /**
     * 添加用户
     *
     * @param user  用户信息
     * @return  返回用户id
     */
    @Override
    public String add(User user) {
        user.setId(UUIDUtil.getUUID());
        user.setRegTime(new Date());
        userMapper.insert(user);
        return user.getId();
    }

    /**
     * 修改用户
     *
     * @param user  用户信息
     * @return  返回用户id
     */
    @Override
    public String oper(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdEqualTo(user.getId());
        if ("".equals(user.getPicImg())){
            user.setPicImg(null);
        }
        System.out.println("==============="+user);
        userMapper.updateByExample(user,userExample);
        return user.getId();
    }

    /**
     * 删除用户
     *
     * @param user  用户信息
     * @return  返回用户id
     */
    @Override
    public String del(User user) {
        userMapper.delete(user);
        return user.getId();
    }

    /**
     * 文件导出功能
     *
     * @return
     */
    @Override
    public String userExport(HttpSession session) {
        List<User> users = userMapper.selectAll();
        String realPath = session.getServletContext().getRealPath("/user/img");

        ArrayList<User> arrayList = new ArrayList<>();
        for (User user : users) {
            user.setPicImg(realPath+"/"+user.getPicImg());
            arrayList.add(user);
        }

        try {

            File savefile = new File("D:/excel/");
            if (!savefile.exists()) {
                savefile.mkdirs();
            }
            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("用户信息","用户"), User .class, arrayList);
            FileOutputStream fos = new FileOutputStream("D:/excel/user.xls");
            workbook.write(fos);
            fos.close();

            return "导出成功";
        } catch (IOException e) {
            e.printStackTrace();
            return "导出失败";
        }

    }

    /**
     * 文件上传
     *
     * @param id
     * @param picImg
     * @param session
     */
    @Override
    public void bannerUpload(String id, MultipartFile picImg, HttpSession session) {
        UserExample userExample = new UserExample();
        //调用上传工具类
        String name = UploadPictureUtil.upload(picImg, session, "/user/img");
        User user = new User();
        user.setId(id);
        if (!"".equals(name)){
            user.setPicImg(name);
        }
        userExample.createCriteria().andIdEqualTo(id);
        userMapper.updateByExampleSelective(user,userExample);
    }

    /**
     *            [2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3]
     *            [2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3]
     *            {"一月":"456"}
     */

    /**
     * 获取用户统数据
     * @return
     */
    @Override
    public HashMap<String,Object> userCount(Integer year){


        if (year==null){
            Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
            int year2 = c.get(Calendar.YEAR);
            year=year2;
        }


        HashMap<String, Object> map = new HashMap<>();

        //创建一个存储男性注册时间的集合
        ArrayList<Integer> mans = new ArrayList<>();
        //获取当年的男性每月注册数据
        for (int i=1;i<=12;i++){
            Integer count = userMapper.userCount(i, year, "男");
            if(count==null){
                count=0;
            }
            mans.add(count);
        }
        map.put("man",mans);

        //创建一个存储女性注册时间的集合
        ArrayList<Integer> womans = new ArrayList<>();
        //获取当年的女性每月注册数据
        for (int i=1;i<=12;i++){
            Integer count = userMapper.userCount(i, year, "女");
            if(count==null){
                count=0;
            }
            womans.add(count);
        }
        map.put("woman",womans);
        map.put("year",year);

        return map;
    }


    /**
     *  [
     *    {name: '北京',value: Math.round(Math.random()*1000)},
     *    {name: '天津',value: Math.round(Math.random()*1000)}
     *  ]
     *
     *
     */
    @Override
    public HashMap<String, Object> userDistribution() {
        List<UserDistribution> mans = userMapper.userDistribution("男");
        List<UserDistribution> woman = userMapper.userDistribution("女");
        HashMap<String, Object> map = new HashMap<>();
        map.put("mans",mans);//男
        map.put("woman",woman);//女

        return map;
    }

}

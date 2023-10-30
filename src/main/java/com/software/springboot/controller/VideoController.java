//package com.rebims.schoolnews.controller;
//
//import com.rebims.schoolnews.dto.Result;
//import com.rebims.schoolnews.dto.VideoDTO;
//import com.rebims.schoolnews.entity.Video;
//import com.rebims.schoolnews.exception.SchoolNewsException;
//import com.rebims.schoolnews.service.VideoService;
//import com.rebims.schoolnews.utils.ResultUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.validation.Valid;
//import java.io.File;
//import java.io.IOException;
//import java.util.UUID;
//
//@RestController
//@RequestMapping("/video")
//public class VideoController {
//    @Value("${video.path}")
//    private String uploadPath;
//    @Autowired
//    private VideoService videoService;
//
//    @PostMapping("/uploadVideo")
//    public Result<String> uploadAvatar(@RequestParam("file") MultipartFile file) {
//        if (file.isEmpty()) {
//            throw new SchoolNewsException("请选择视频");
//        }
//        // 获取名字
//        String name = file.getOriginalFilename();
//        // 创建文件对象
//        File temp = new File(uploadPath);
//        if (!temp.exists()) {
//            temp.mkdirs();
//        }
//        // 获取原始文件的索引
//        Integer endIndexOf = name.lastIndexOf(".");
//        // 上传文件后的后缀名
//        String endFile = name.substring(endIndexOf, name.length());
//        // 新名字
//        String newName = UUID.randomUUID().toString() + endFile;
//        String path = uploadPath + newName;
//        // 放到返回值中,给前端返回
//        // 正式上传的文件
//        File localFile = new File(path);
//        try {
//            file.transferTo(localFile);
//        } catch (IOException e) {
//            throw new RuntimeException("上传失败", e);
//        }
//        return ResultUtil.success(path);
//    }
//
//    @PostMapping("/publish")
//    public Result<Video> publishVideo(@Valid @RequestBody Video video, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            throw new SchoolNewsException(bindingResult.getFieldError().getDefaultMessage());
//        }
//        Video publish = videoService.publish(video);
//        return ResultUtil.success(publish);
//    }
//    @GetMapping("/pageList")
//    public Result<Page<VideoDTO>> pageList(@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
//                                           @RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
//                                           Video video){
//        if (page < 1) {
//            throw new SchoolNewsException("【查询视频】：当面页码不能小于1");
//        }
//        Sort.Order publishDate = Sort.Order.desc("publishDate");
//        Sort sort = Sort.by(publishDate);
//        Pageable pageable = PageRequest.of(page - 1, size, sort);
//        Page<VideoDTO> pageResult = videoService.findVideoInfo(video, pageable);
//        return ResultUtil.success(pageResult);
//    }
//
//}

package com.yunyaotai.yunyaotai.api;

import com.yunyaotai.yunyaotai.dto.CommentDto;
import com.yunyaotai.yunyaotai.entity.Comment;
import com.yunyaotai.yunyaotai.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
public class CommentApi {
    @Autowired
    CommentService commentService;

    //添加一条留言信息
    //接口地址  Post http://localhost:8080/drug/api/comment
    @PostMapping
    public String addComment(@RequestBody CommentDto commentDto){
        commentService.addComment(commentDto);
        return "ok";
    }


    //根据管理员id获得全部留言，即取出全部用户对某位管理员的所有留言
    //接口地址 Get http://localhost:8080/drug/api/comment/searchadmin?id=3&page=0&size=5
    @GetMapping("searchadmin")
    public Page<Comment> getAllCommentsByAdminId(Integer id,int page,int size){
        return commentService.getAllCommentByAdminId(id,PageRequest.of(page,size));
    }


    //根据用户id获得全部留言
    //接口地址 Get http://localhost:8080/drug/api/comment/searchuser?id=3&page=0&size=5
    @GetMapping("searchuser")
    public Page<Comment> getAllCommentsByUserId(Integer id,int page,int size){
        return commentService.getAllCommentByUserId(id,PageRequest.of(page,size));
    }


    //删除留言
    @DeleteMapping("{id}")
    public String deleteComment(@PathVariable("id") Integer id){
        return commentService.deleteComment(id) ? "ok" : "false";
    }


}

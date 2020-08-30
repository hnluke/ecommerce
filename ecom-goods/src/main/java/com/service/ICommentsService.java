package com.service;

import com.model.Comments;

import java.util.List;

public interface ICommentsService {
    // 新增商品评论
    public boolean insertComment(Comments comments);

    // 查询商品评论
    public List<Comments> findCommentById(Integer commId);

    // 关联查询商品评论
    public List<Comments> findCommentUnionById(Integer commId);


}

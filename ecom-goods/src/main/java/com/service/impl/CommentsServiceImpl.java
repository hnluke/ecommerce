package com.service.impl;

import com.mapper.CommentsMapper;
import com.mapper.UnionMapper;
import com.model.Comments;
import com.service.ICommentsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommentsServiceImpl implements ICommentsService {
    @Resource
    CommentsMapper commentsMapper;
    @Resource
    UnionMapper unionMapper;
    // 新增评论
    @Override
    public boolean insertComment(Comments comments) {
        return commentsMapper.insertComment(comments);
    }

    // 查询评论
    @Override
    public List<Comments> findCommentById(Integer commId) {
        return commentsMapper.findCommentById(commId);
    }

    @Override
    public List<Comments> findCommentUnionById(Integer commId) {
        return unionMapper.queryCommentByGoodId(commId);
    }
}

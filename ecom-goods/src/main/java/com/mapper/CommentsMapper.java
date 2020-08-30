package com.mapper;

import com.model.Comments;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentsMapper {

    /**
     * 新增评论
     * @param comments
     * @return
     */
    public boolean insertComment(Comments comments);

    /**
     * 删除评论
     * @param commId
     * @return
     */
    public boolean deleteComment(Integer commId);

    /**
     * 更新评论
     * @param comments
     * @return
     */
    public boolean updateComment(Comments comments);

    /**
     * 查询评论
     * @param commId
     * @return
     */
    public List<Comments> findCommentById(Integer commId);


}

package com.mapper;
import com.model.Comments;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UnionMapper {
    public List<Comments> queryCommentByGoodId(Integer commId);
}

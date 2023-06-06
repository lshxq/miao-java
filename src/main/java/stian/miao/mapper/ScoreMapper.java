package stian.miao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import stian.miao.entity.AccessEntity;
import stian.miao.entity.ScoreEntity;

@Mapper
public interface ScoreMapper {
	int updateRecord(ScoreEntity score);
	
	int insertRecord(ScoreEntity score);
	
	List<ScoreEntity> getTop10();
	
	int newAccess(AccessEntity entity);
}

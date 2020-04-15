package ActivityManager.mapper;

import ActivityManager.model.ActivitiesInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ActivitiesInfoMapper {
    //分页查询所有活动信息
    List<ActivitiesInfo> selectAllActivitiesInfos(Map<String,Object> parmMap);
    //查询活动的总量
    int selectActivitiesNum();
   //增加新活动
    int insertActivity(ActivitiesInfo  activitiesInfo);
    //更新活动
    int updateActivity(ActivitiesInfo  activitiesInfo);
    //删除活动
    int deleteActivity(String id);
    //根据id查询活动信息
    ActivitiesInfo selectActivitiesInfoById(String id);
}
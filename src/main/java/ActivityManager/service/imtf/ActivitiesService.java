package ActivityManager.service.imtf;

import ActivityManager.model.ActivitiesInfo;


import java.util.List;
import java.util.Map;

public interface ActivitiesService {
    /**
     * 分页查询活动信息
     */
    List<ActivitiesInfo> getAllActivitiesInfos(Map<String,Object> parmMap);
    /**
     * 查询数据总数
     */
    int  getActivitiesNum();

    /**
     * 新增活动
     * @param activitiesInfo
     * @return
     */
    int addActivity(ActivitiesInfo activitiesInfo);

    /**
     * 修改活动
     * @param activitiesInfo
     * @return
     */
    int updateActivity(ActivitiesInfo activitiesInfo);

    /**
     * 根据id删除活动
     * @param id
     * @return
     */
    int deleteActivity(String id);

    /**
     * 根据id获取活动信息
     * @param id
     * @return
     */
    ActivitiesInfo  getActvityById(String id);
}


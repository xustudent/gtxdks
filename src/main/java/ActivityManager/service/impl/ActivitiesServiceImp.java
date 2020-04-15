package ActivityManager.service.impl;

import ActivityManager.mapper.ActivitiesInfoMapper;
import ActivityManager.model.ActivitiesInfo;
import ActivityManager.service.imtf.ActivitiesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ActivitiesServiceImp implements ActivitiesService {
    @Autowired
    private ActivitiesInfoMapper activitiesInfoMapper;

    @Override
    public List<ActivitiesInfo> getAllActivitiesInfos(Map<String,Object> parmMap) {
        return activitiesInfoMapper.selectAllActivitiesInfos(parmMap);
    }

    @Override
    public int getActivitiesNum() {
        return activitiesInfoMapper.selectActivitiesNum();
    }

    @Override
    public int addActivity(ActivitiesInfo activitiesInfo) {
       return activitiesInfoMapper.insertActivity(activitiesInfo);
    }

    @Override
    public int updateActivity(ActivitiesInfo activitiesInfo) {
        return activitiesInfoMapper.updateActivity(activitiesInfo);
    }

    @Override
    public int deleteActivity(String id) {
        return activitiesInfoMapper.deleteActivity(id);
    }

    @Override
    public ActivitiesInfo getActvityById(String id) {
        return activitiesInfoMapper.selectActivitiesInfoById(id);
    }
}

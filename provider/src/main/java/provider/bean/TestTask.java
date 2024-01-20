package provider.bean;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TestTask {
    public String activityCode;
    public List<TaskInfo> taskList;
}

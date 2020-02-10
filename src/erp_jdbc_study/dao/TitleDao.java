package erp_jdbc_study.dao;

import java.util.List;

import erp_jdbc_study.dto.Title;

public interface TitleDao {
	Title selectTitleByNo(Title title);
	List<Title> selectTitleByAll();
	
	int insertTitle(Title title);
	int updateTitle(Title title);
	int deleteTitle(Title title);
}

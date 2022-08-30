package aa.bb.cc;

import java.util.List;






import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class Dao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	
	public List<DTO> boardlistmain() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("Query.listAll");
	}


	public List<DTO> listAll2(String b) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("Query.listAll2",b);
	}

}

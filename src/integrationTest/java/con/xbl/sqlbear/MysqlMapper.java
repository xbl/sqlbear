package con.xbl.sqlbear;

import org.apache.ibatis.annotations.Select;

public interface MysqlMapper {

    @Select("SELECT count(1) FROM `order`")
    public int getCount();
}

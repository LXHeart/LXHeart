package com.lin.dao;  
  
  
import com.lin.domain.User;  
  
/** 
 * ���ܸ�Ҫ��User��DAO�� 
 *  
 * @author linbingwen 
 * @since 2015��9��28�� 
 */  
public interface UserDao {  
    /** 
     *  
     * @author linbingwen 
     * @since 2015��9��28�� 
     * @param userId 
     * @return 
     */  
    public User selectUserById(Integer userId);  
  
}  
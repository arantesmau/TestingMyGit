package org.se.lab;

import java.util.List;

public interface UserTable
{
    void insert(final User user);
    void update(final User user);    
    void delete(final long id);
    
    User findById(final long id);
    List<User> findAll();
}
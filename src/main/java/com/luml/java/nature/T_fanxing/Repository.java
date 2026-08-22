package com.luml.source.nature.T_fanxing;

import com.luml.domain.User;

/**
 * 3. 泛型接口
 * 常见于数据访问层或规范定义。
 * @param <T>
 */

// 定义泛型接口
public interface Repository<T>  {
    void save(T entity);
    T findById(int id);
}

// 实现方式一：实现时指定具体类型
class UserRepository implements Repository<User> {
    @Override
    public void save(User user) { /* ... */ }
    @Override
    public User findById(int id) { return null; }
}

// 实现方式二：继续保留泛型（延迟指定）
class AbstractRepository<T> implements Repository<T> {
    @Override
    public void save(T entity) { /* ... */ }
    @Override
    public T findById(int id) { return null; }
}

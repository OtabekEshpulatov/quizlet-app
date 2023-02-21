package com.nooglers.dao;


import com.nooglers.domains.AppCookie;
import jakarta.servlet.http.Cookie;

public class CookieDao extends BaseDAO<AppCookie, String> {

    private static final ThreadLocal<CookieDao> cookieDaoLocal = ThreadLocal.withInitial(CookieDao::new);

    public static CookieDao getInstance() {

        return cookieDaoLocal.get();
    }


    public boolean removeCookie(Integer userId) {
        begin();
        final int i = entityManager.createQuery("delete from cookie c where c.user.id=?1")
                .setParameter(1 , userId).executeUpdate();

        commit();
        return i!=0;
    }
}

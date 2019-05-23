package cn.com.filter;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.util.Destroyable;

@SuppressWarnings({"All"})
public class CacheManager<K, V> implements Cache<K, V> , Destroyable {
    private EhCacheManager cacheManager;

    private Cache<K, V> cache = null;

    public Cache<K, V> getCache() {
        try {
            if(null == cache){
                cache = cacheManager.getCache("shiro_cache");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return cache;
    }

    @Override
    public void clear() throws CacheException {
        getCache().clear();
    }

    @Override
    public V get(K key) throws CacheException {
        return getCache().get(key);
    }

    @Override
    public Set<K> keys() {

        return getCache().keys();
    }

    @Override
    public V put(K key, V value) throws CacheException {
        return getCache().put(key, value);
    }

    @Override
    public V remove(K key) throws CacheException {
        return getCache().remove(key);
    }

    @Override
    public int size() {
        return getCache().size();
    }

    @Override
    public Collection<V> values() {
        return getCache().values();
    }

    EhCacheManager getCacheManager() {
        return cacheManager;
    }

    void setCacheManager(EhCacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    void setCache(Cache<K, V> cache) {
        this.cache = cache;
    }

    /**
     * 获取所有 对象
     */
    public Collection<V> getAllSession(){
        Set<V> S = new HashSet<>();
        try {
            cache = getCache();
            Collection<V> values = cache.values();
            for (V v : values) {
                if(isBlank(v)){
                    S.add((V)v);
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return S;
    }

    /**
     * 一次性判断多个或单个对象为空。
     * @param objects
     * @author zhou-baicheng
     * @return 只要有一个元素为Blank，则返回true
     */
    boolean isBlank(Object...objects){
        Boolean result = false ;
        for (Object object : objects) {
            if(null == object || "".equals(object.toString().trim())
                    || "null".equals(object.toString().trim())){
                result = true ;
                break ;
            }
        }
        return result ;
    }

    @Override
    public void destroy(){
        cacheManager.destroy();
    }
}
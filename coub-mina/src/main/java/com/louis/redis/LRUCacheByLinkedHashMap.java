package com.louis.redis;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCacheByLinkedHashMap<K, V> extends LinkedHashMap<K, V> {
      private final int CACHE_SIZE;
  
      /**
       * 传递进来最多能缓存多少数据
       *
       * @param cacheSize 缓存大小
       */
      public LRUCacheByLinkedHashMap(int cacheSize) {
          // true 表示让 linkedHashMap 按照访问顺序来进行排序，最近访问的放在头部，最老访问的放在尾部。
          super((int) Math.ceil(cacheSize / 0.75) + 1, 0.75f, true);
          CACHE_SIZE = cacheSize;
      }

    /**
     * 当缓存的容量达到上限的时候，会调用这个方法，判断是否需要移除最老的数据。
     * @param eldest The least recently inserted entry in the map, or if
     *           this is an access-ordered map, the least recently accessed
     *           entry.  This is the entry that will be removed it this
     *           method returns {@code true}.  If the map was empty prior
     *           to the {@code put} or {@code putAll} invocation resulting
     *           in this invocation, this will be the entry that was just
     *           inserted; in other words, if the map contains a single
     *           entry, the eldest entry is also the newest.
     * @return
     */
      @Override
      protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
          // 当 map中的数据量大于指定的缓存个数的时候，就自动删除最老的数据。
          return size() > CACHE_SIZE;
      }
  }
/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.volley;

import java.util.Collections;
import java.util.Map;

/**
 * An interface for a cache keyed by a String with a byte array as data.
 * 缓存接口
 */
public interface Cache {
    /**
     * 从缓存中获取缓存实例
     * Retrieves an entry from the cache.
     * @param key Cache key
     * @return An {@link Entry} or null in the event of a cache miss
     */
    public Entry get(String key);

    /**
     * Adds or replaces an entry to the cache.
     * @param key Cache key
     * @param entry Data to store and metadata for cache coherency, TTL, etc.
     * 存放缓存
     */
    public void put(String key, Entry entry);

    /**
     * Performs any potentially long-running actions needed to initialize the cache;
     * will be called from a worker thread.
     * 初始化缓存
     */
    public void initialize();

    /**
     * Invalidates an entry in the cache.
     * @param key Cache key
     * @param fullExpire True to fully expire the entry, false to soft expire
     * Entry在缓存中无效
     */
    public void invalidate(String key, boolean fullExpire);

    /**
     * Removes an entry from the cache.
     * @param key Cache key
     * 从缓存中移除entry
     */
    public void remove(String key);

    /**
     * Empties the cache.
     * 
     * 清空缓存
     */
    public void clear();

    /**
     * Data and metadata for an entry returned by the cache.
     * Entry 缓存记录
     */
    public static class Entry {
        /** The data returned from cache.  存储缓存数据的byte数组*/
        public byte[] data;

        /** ETag for cache coherency. */
        public String etag;

        /** Date of this response as reported by the server. 服务器的时间*/
        public long serverDate;

        /** The last modified date for the requested object. 最后修改时间*/
        public long lastModified;

        /** TTL for this record. 缓存过期的时间*/
        public long ttl;

        /** Soft TTL for this record.  缓存新鲜时间*/
        public long softTtl;

        /** Immutable response headers as received from server; must be non-null. map集合用于保存请求的url和数据 */
        public Map<String, String> responseHeaders = Collections.emptyMap();

        /** True if the entry is expired.  判断缓存是否过期  如果过期返回 true*/
        public boolean isExpired() {
            return this.ttl < System.currentTimeMillis();
        }

        /** True if a refresh is needed from the original data source.  判断缓存是否需要刷新  如果需要刷新返回true*/
        public boolean refreshNeeded() {
            return this.softTtl < System.currentTimeMillis();
        }
    }

}

package com.demo.java.utils.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisSupport {

    private final JedisPool jedisPool;
    private JedisPoolConfig jedisPoolConfig;
    private String host;
    private int port;

    public JedisPoolConfig getJedisPoolConfig() {
        return jedisPoolConfig;
    }

    public void setJedisPoolConfig(JedisPoolConfig jedisPoolConfig) {
        this.jedisPoolConfig = jedisPoolConfig;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public JedisSupport(JedisPoolConfig jedisPoolConfig, String host, int port) {
        this.host = host;
        this.port = port;
        this.jedisPoolConfig = jedisPoolConfig;
        this.jedisPool = new JedisPool(jedisPoolConfig, host, port);
    }

    public boolean set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedisPool.returnResource(jedis);
        }
        return false;
    }

    public String get(String key) {
        Jedis jedis = null;
        String val = null;
        try {
            jedis = jedisPool.getResource();
            val = jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedisPool.returnResource(jedis);
        }
        return val;
    }

    public void delete(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.del(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedisPool.returnResource(jedis);
        }
    }

    public void delete(String[] keys) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.del(keys);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedisPool.returnResource(jedis);
        }
    }
}

package com.xtt.platform.framework.core.interceptor;

import org.aspectj.lang.JoinPoint;

import com.xtt.platform.util.PrimaryKeyUtil;

public class MyBaitsInsertInterceptor {

	public void doBefore(JoinPoint jp) {
		Object entity = jp.getArgs()[0];
		setPrimaryKey(entity, entity.getClass());
	}

	public void doThrowing(JoinPoint jp, Throwable ex) {
		System.out.println("method " + jp.getTarget().getClass().getName() + "." + jp.getSignature().getName() + " throw exception");
		System.out.println(ex.getMessage());
	}

	/**
	 * 主键自动生成
	 * 
	 * @Title: setPrimaryKey
	 * @param entity
	 * @param clazz
	 *
	 */
	private void setPrimaryKey(Object entity, Class<? extends Object> clazz) {
		try {
			Long id = (Long) clazz.getDeclaredMethod("getId").invoke(entity);
			if (id != null) {
				return;
			}
			Integer tenantId = (Integer) clazz.getDeclaredMethod("getFkTenantId").invoke(entity);
			String className = clazz.getName().substring(clazz.getName().lastIndexOf(".") + 1);
			id = PrimaryKeyUtil.getPrimaryKey(className, tenantId);
			clazz.getDeclaredMethod("setId", Long.class).invoke(entity, id);
		} catch (Exception e1) {
			if (clazz == null || clazz.getSuperclass() == null) {
				return;
			}
			setPrimaryKey(entity, clazz.getSuperclass());
		}
	}
}
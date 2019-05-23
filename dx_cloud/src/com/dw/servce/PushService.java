package com.dw.servce;

import com.dw.model.PushMenu;

public interface PushService {

	/**
	 * @deprecated进行写入 非实时 时延数据的表格
	 * 
	 * @param menu
	 * @return
	 */
	int delReal(PushMenu menu);

	/**
	 * @deprecated写入故障时长的数据表
	 * 
	 * @param menu
	 * @return
	 */
	int fail(PushMenu menu);

	/**
	 * @deprecated进程数据和进程可用率
	 * 
	 * @param menu
	 * @return
	 */
	int availableProc(PushMenu menu);

	/**
	 * @deprecated进程的故障时长
	 * 
	 * @param menu
	 * @return
	 */
	int failProc(PushMenu menu);

	/**
	 * @deprecated进行写入 实时 时延数据的表格
	 * 
	 * @param menu
	 * @return
	 */
	int NondelReal(PushMenu menu);

}

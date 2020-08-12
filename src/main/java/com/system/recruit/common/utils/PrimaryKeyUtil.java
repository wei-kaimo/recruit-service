package com.system.recruit.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.atomic.AtomicLong;

/**
 *
 * @Package com.ztesoft.zsmart.rims.util
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author
 * @date 2015年12月2日 下午2:38:33
 */
public class PrimaryKeyUtil {

	public static void main(String[] args) {
		PrimaryKeyUtil primaryKeyUtil = new PrimaryKeyUtil();
		System.out.println(primaryKeyUtil.getShort14PrimaryKey());
	}

	/**
	 * Service标识
	 */
	private static String serviceId = "852" ;

	private static Integer seqNumber=100;

	/**
	 * 主键自增序列
	 */
	private static AtomicLong short16PrimaryKeyAtomicLong = new AtomicLong();

	private static AtomicLong short20PrimaryKeyAtomicLong = new AtomicLong();

	private static AtomicLong short24PrimaryKeyAtomicLong = new AtomicLong();

	private static AtomicLong short30PrimaryKeyAtomicLong = new AtomicLong();

	/**
	 * 随机数长度最大值
	 */
	private static java.util.Map<Integer,Long> maxRandomMap = new java.util.HashMap<Integer,Long>();

	/**
	 * 初始化Service标识
	 */
	static{
		String tempServiceId = System.getProperty("serviceid");
		if (tempServiceId!=null && !"".equals(tempServiceId)) {
			PrimaryKeyUtil.serviceId = tempServiceId;
		}
	}

	public static String getServer_id(){
		String serviceid="852";
		if(System.getProperty("serviceid")!=null && "".equals(System.getProperty("serviceid"))){
			serviceid=System.getProperty("serviceid");
		}
		return serviceid;
	}

	/**
	 *
	 * @Description: 主键生成 	注意:计算主键总长度为[2位年份]+[3位当天在一年的天数]+[6位时分秒HHmmss]+[3为随机数] ，建议用在页面手工录入的主键生成
	 * @return
	 * @date 2015年11月11日 上午9:56:14
	 */
	public static String getShort14PrimaryKey(){
		Calendar calendar =Calendar.getInstance();
		StringBuilder randomBuffer = new StringBuilder();
		// 增加时间[2位年]
		randomBuffer.append(calendar.get(Calendar.YEAR)-2000);
		// 增加 [当天在一年的天数]+[HHmmss]
		randomBuffer.append(String.format("%03d",calendar.get(Calendar.DAY_OF_YEAR))).append(new SimpleDateFormat("HHmmss").format(calendar.getTime()));
		// 如果当前主键自增序列等于随机数长度最大值.则把数字设置为0
		PrimaryKeyUtil.short16PrimaryKeyAtomicLong.compareAndSet(getMaxRandom(6), 0);
		// 获取当前序列值
		seqNumber=seqNumber+1;
		if(seqNumber>=1000){
			seqNumber=100;
		}
		randomBuffer.append(seqNumber);

		return randomBuffer.toString();
	}

	/**
	 *
	 * @Description: 主键生成 	注意:计算主键总长度为[2位年份]+[3为Service编码]+[3位当天在一年的天数]+[4位时分]+[4位随机数] ，建议用在页面手工录入的主键生成
	 * @return
	 * @date 2015年11月11日 上午9:56:14
	 */
	public static String getShort16PrimaryKey(){
		Calendar calendar =Calendar.getInstance();
		StringBuilder randomBuffer = new StringBuilder();
		// 增加时间[2位年]
		randomBuffer.append(calendar.get(Calendar.YEAR)-2000);
		//增加Service标识
		randomBuffer.append(PrimaryKeyUtil.serviceId);
		// 增加 [当天在一年的天数]+[HHmmss]
		randomBuffer.append(String.format("%03d",calendar.get(Calendar.DAY_OF_YEAR))).append(new SimpleDateFormat("HHmm").format(calendar.getTime()));;
		// 如果当前主键自增序列等于随机数长度最大值.则把数字设置为0
		PrimaryKeyUtil.short16PrimaryKeyAtomicLong.compareAndSet(getMaxRandom(4), 0);
		// 获取当前序列值
		long currentSeqValue = PrimaryKeyUtil.short16PrimaryKeyAtomicLong.incrementAndGet();
		// 补0操作 (%固定写法,0为补充什么值,randomLength补多少位,d为参数是正数型 )
		randomBuffer.append(String.format(new StringBuffer().append("%0").append(4).append("d").toString(), currentSeqValue));

		return randomBuffer.toString();
	}

	/**
	 *
	 * @Description: 主键生成 	注意:计算主键总长度为[2位年份]+[3为Service编码]+[3位当天在一年的天数]+[6位时分秒HHmmss]+[6位随机数]
	 * @return
	 * @date 2015年11月11日 上午9:56:14
	 */
	public static String getShort20PrimaryKey(){
		Calendar calendar =Calendar.getInstance();
		StringBuilder randomBuffer = new StringBuilder();
		// 增加时间[2位年]
		randomBuffer.append(calendar.get(Calendar.YEAR)-2000);
		//增加Service标识
		randomBuffer.append(PrimaryKeyUtil.serviceId);
		// 增加 [当天在一年的天数]+[HHmmss]
		randomBuffer.append(String.format("%03d",calendar.get(Calendar.DAY_OF_YEAR))).append(new SimpleDateFormat("HHmmss").format(calendar.getTime()));
		// 如果当前主键自增序列等于随机数长度最大值.则把数字设置为0
		PrimaryKeyUtil.short20PrimaryKeyAtomicLong.compareAndSet(getMaxRandom(6), 0);
		// 获取当前序列值
		long currentSeqValue = PrimaryKeyUtil.short20PrimaryKeyAtomicLong.incrementAndGet();
		// 补0操作 (%固定写法,0为补充什么值,randomLength补多少位,d为参数是正数型 )
		randomBuffer.append(String.format(new StringBuffer().append("%0").append(6).append("d").toString(), currentSeqValue));

		return randomBuffer.toString();
	}

	/**
	 *
	 * @Description: 主键生成 	注意:计算主键总长度为[2位年份]+[3为Service编码]+[13位月日时分秒毫秒MMddHHmmssSSS]+[6位随机数]
	 * @return
	 * @date 2015年11月10日 下午4:33:20
	 */
	public static String getShort24PrimaryKey() {
		StringBuilder randomBuffer = new StringBuilder();
		Calendar calendar =Calendar.getInstance();
		// 增加时间[2位年]
		randomBuffer.append(calendar.get(Calendar.YEAR)-2000);
		// 增加Service标识
		randomBuffer.append(PrimaryKeyUtil.serviceId);
		// 增加时间
		randomBuffer.append(new SimpleDateFormat("MMddHHmmssSSS").format(calendar.getTime()));
		// 如果当前主键自增序列等于随机数长度最大值.则把数字设置为0
		PrimaryKeyUtil.short24PrimaryKeyAtomicLong.compareAndSet(getMaxRandom(6), 0);
		// 获取当前序列值
		long currentSeqValue = PrimaryKeyUtil.short24PrimaryKeyAtomicLong.incrementAndGet();
		// 补0操作 (%固定写法,0为补充什么值,randomLength补多少位,d为参数是正数型 )
		randomBuffer.append(String.format(new StringBuffer().append("%0").append(6).append("d").toString(), currentSeqValue));

		return randomBuffer.toString();
	}

	/**
	 *
	 * @Description: 主键生成 	注意:计算主键总长度为[2位年份]+[3为Service编码]+[6位地市编码]+[13位月日时分秒毫秒MMddHHmmssSSS]+[6位随机数]
	 * @param 6位地市编码
	 * @return
	 * @date 2015年11月10日 下午4:33:20
	 */
	public static String getShort30PrimaryKey(String cityCode) {
		if(cityCode==null || "".equals(cityCode)){
			cityCode = "000000";
		}
		StringBuilder randomBuffer = new StringBuilder();
		Calendar calendar =Calendar.getInstance();
		// 增加时间[4位年]
		randomBuffer.append(calendar.get(Calendar.YEAR)-2000);
		// 增加Service标识
		randomBuffer.append(PrimaryKeyUtil.serviceId);
		// 增加地市编码
		randomBuffer.append(cityCode);
		// 增加时间
		randomBuffer.append(new SimpleDateFormat("MMddHHmmssSSS").format(calendar.getTime()));
		// 如果当前主键自增序列等于随机数长度最大值.则把数字设置为0
		PrimaryKeyUtil.short30PrimaryKeyAtomicLong.compareAndSet(getMaxRandom(6), 0);
		// 获取当前序列值
		long currentSeqValue = PrimaryKeyUtil.short30PrimaryKeyAtomicLong.incrementAndGet();
		// 补0操作 (%固定写法,0为补充什么值,randomLength补多少位,d为参数是正数型 )
		randomBuffer.append(String.format(new StringBuffer().append("%0").append(6).append("d").toString(), currentSeqValue));

		return randomBuffer.toString();
	}


	/**
	 *
	 * @Description: 默认标识生成  格式:  [2位年份]+[3为Service编码]+[13位月日时分秒毫秒MMddHHmmssSSS]+[6位随机数]
	 * @return
	 * @date 2015年11月10日 下午4:33:00
	 */
	public static String getDefaultPrimaryKey(){
		return getShort24PrimaryKey();
	}

	/**
	 *
	 * @Description: 获取随机数长度最大值
	 * @param randomLength  随机数长度
	 * @return
	 * @author zhouqiangang
	 * @date 2015年11月10日 下午4:31:55
	 */
	public static synchronized long getMaxRandom(int randomLength){
		Long maxRandom = PrimaryKeyUtil.maxRandomMap.get(randomLength);
		if(null == maxRandom || String.valueOf(maxRandom).length() != randomLength){
			StringBuffer strBuffer = new StringBuffer();
			for (int i = 0; i < randomLength; i++) {
				strBuffer.append("9");
			}
			maxRandom = new Long(strBuffer.toString());
			PrimaryKeyUtil.maxRandomMap.put(randomLength, maxRandom);
		}
		return maxRandom;
	}
}

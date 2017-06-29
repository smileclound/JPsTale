package org.pstale.utils;

import com.jme3.math.Vector3f;

/**
 * 游戏中的时间系统
 * 
 * @author yanmaoyuan
 * 
 */
public class GameDate {
    // 我们假设游戏中时间流动的速度是现实的多少倍！
    public final static float SPEED = 20;

    public final static float GAME_SECOND = 1 / SPEED;
    public final static int GAME_MINUTE = (int) (60 * GAME_SECOND);// 游戏1分钟 = 60
                                                                   // * 游戏秒
    public final static int GAME_HOUR = GAME_MINUTE * 60;// 游戏1小时 = 60游戏分钟
    public final static int GAME_DAY = GAME_HOUR * 24;// 游戏1天 = 24游戏小时
    public final static int GAME_MONTH = GAME_DAY * 30;// 游戏1月 =30游戏天
    public final static int GAME_YEAR = GAME_MONTH * 12;// 游戏1年 = 12游戏月

    // 地球黄赤交角为23°26′
    private final static double ECLIPTIC_OBLIQUITY = Math.PI * 23.5f / 180f;
    // 假设世界的纬度为30°N，春分时中午12点太阳高度为60°
    private final static double WUHAN_LATITUDE = Math.PI * 60f / 180f;

    private final static double SEC_DEG = 2 * Math.PI / GAME_DAY;// 地球每秒转动的角度

    private final static double SIN_HOUR_DEG = Math.sin(Math.PI / 12);// 日出一小时的高度

    private long totalSec;// 游戏从开始到现在总共经过的秒数

    private int year_sec;
    private int month_sec;
    private int date_sec;
    private int hour_sec;

    private int year;// 年份>=0
    private int month;// 月份 [0~11]
    private int date;// 日期 [0~29]
    private int day;// 一年中的第几天[0~359]

    private int hour;// 小时[0~23]
    private int minute;// 分钟[0~59]

    private double alpha;// 我们假设6点钟日出，α代表时针相对于6点钟的位置。

    private double theta;// 我们假设一年每个月正午12点阳光的高度为θ

    public GameDate() {
        totalSec = 0l;
        sunDirection = new Vector3f();

        updateTime();
    }

    public GameDate(long lastTime) {
        totalSec = (long) (lastTime * GAME_SECOND);
        sunDirection = new Vector3f();

        updateTime();
    }

    float time = 0;

    public void update(float tpf) {
        time += tpf;
        if (time >= GAME_SECOND) {
            totalSec++;
            updateTime();

            time -= GAME_SECOND;
        }
    }

    private void updateTime() {

        //////////// 年月日
        year_sec = (int) (totalSec % GAME_YEAR);// 游戏中的一年过了多少秒。
        year = (int) (totalSec / GAME_YEAR);// 经过了几年了？
        day = year_sec / GAME_DAY;// 这是一年的第几天？

        month_sec = year_sec % GAME_MONTH;// 游戏中一个月过了多少秒
        month = year_sec / GAME_MONTH;// 经过了几个月了？

        date_sec = month_sec % GAME_DAY;// 游戏中的一天过了多少秒
        date = month_sec / GAME_DAY;// 经过了几天了？

        ///////////// 时分秒
        hour_sec = date_sec % GAME_HOUR;// 游戏中的一小时过了多少秒
        hour = date_sec / GAME_HOUR;// 今天过了几小时了？

        minute = (int) (hour_sec / GAME_MINUTE);// 一小时过了几分钟了？

        // 我们假设6点钟日出，α代表时针相对于6点钟的位置。
        alpha = SEC_DEG * (date_sec - GAME_HOUR * 6);// 根据一天的时间，计算时钟的角度
        updateDayAndNight();

        theta = getTheta();// 根据地球公转的角度，计算日照高度。
        updateSunDirection();
    }

    /**
     * 游戏从开始到现在总共经过的秒数
     * 
     * @return
     */
    public long currentTimeInSecond() {
        return totalSec;
    }

    /**
     * 下面来计算太阳高度。太阳每天升起的高度都不一样，随地球公转而变化。
     */
    public double getTheta() {

        // 春分是春三月的中节，因此日期要回退45天
        double year_angle = Math.PI * 2 * (day - 45) / 360;

        // 世界的实际日照角度为
        this.theta = WUHAN_LATITUDE + ECLIPTIC_OBLIQUITY * Math.sin(year_angle);

        return theta;
    }

    private float lightPower;// 光照强度
    private boolean isDay;// 是否是白天

    public void updateDayAndNight() {
        // 计算阳光强度
        // 日出和日落时，太阳的亮度会渐变，当高度达到PI/6的时候，天就整个亮了。
        // 让日出时间提前1个小时，让日落时间推后1个小时。
        lightPower = (float) ((Math.sin(alpha) + SIN_HOUR_DEG) * 2);
        if (lightPower > 1f)
            lightPower = 1f;

        if (lightPower < 0) {// 太阳落下了
            lightPower = 0f;
            if (isDay)
                isDay = false;// 黑夜
        } else {
            if (!isDay)
                isDay = true;// 白天
        }
    }

    public float getLightPower() {
        return lightPower;
    }

    public boolean isDay() {
        return isDay;
    }

    private Vector3f sunDirection;// 光照角度

    /**
     * 下面来计算光照角度
     */
    public void updateSunDirection() {
        double x = -Math.cos(alpha);
        double y = -Math.sin(alpha) * Math.sin(theta);
        double z = -Math.sin(alpha) * Math.cos(theta);

        sunDirection.set((float) x, (float) y, (float) z);
    }

    public Vector3f getSunDirection() {
        return sunDirection;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDate() {
        return date;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }
}
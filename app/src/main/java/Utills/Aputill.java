package Utills;

import android.content.Context;
import android.view.animation.Interpolator;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.os.ConfigurationCompat;

import com.bumptech.glide.Glide;
import com.example.jsonparsing.R;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class Aputill {
    //Interpolatro for Animation
    private static Interpolator fastOutSlowIn;

    // Get timestamp of start of day 00:00:00
    public static long getStartOfDayTimestamp(Calendar calendar) {
        Calendar newCalendar = Calendar.getInstance(TimeZone.getDefault());
        newCalendar.setTimeInMillis(calendar.getTimeInMillis());
        newCalendar.set(Calendar.HOUR_OF_DAY, 0);
        newCalendar.set(Calendar.MINUTE, 0);
        newCalendar.set(Calendar.SECOND, 0);
        newCalendar.set(Calendar.MILLISECOND, 0);
        return newCalendar.getTimeInMillis();
    }

    //Get timestamp of end of day 23:59:59
    public static long getEndOfDayTimestamp(Calendar calendar) {
        Calendar newCalendar = Calendar.getInstance(TimeZone.getDefault());
        newCalendar.setTimeInMillis(calendar.getTimeInMillis());
        newCalendar.set(Calendar.HOUR_OF_DAY, 23);
        newCalendar.set(Calendar.MINUTE, 59);
        newCalendar.set(Calendar.SECOND, 59);
        newCalendar.set(Calendar.MILLISECOND, 0);
        return newCalendar.getTimeInMillis();
    }

    // Add days to calendar and return result
    public static Calendar addDays(Calendar cal, int days) {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        calendar.setTimeInMillis(cal.getTimeInMillis());
        calendar.add(Calendar.DATE, days);
        return calendar;
    }

    public static String getTime(Calendar calendar, Context context) {
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        String hourString;
        if (hour < 10) {
            hourString = String.format(Locale.getDefault(), context.getString(R.string.zero_label), hour);
        } else {
            hourString = String.format(Locale.getDefault(), "%d", hour);
        }
        String minuteString;
        if (minute < 10) {
            minuteString = String.format(Locale.getDefault(), context.getString(R.string.zero_label), minute);
        } else {
            minuteString = String.format(Locale.getDefault(), "%d", minute);
        }
        return hourString + ":" + minuteString;
    }



    public static void setWeatherIcon(Context context, AppCompatImageView imageView, int weatherCode) {
        if (weatherCode / 100 == 2) {
            Glide.with(context).load(R.drawable.ic_storm_weather).into(imageView);
        } else if (weatherCode / 100 == 3) {
            Glide.with(context).load(R.drawable.ic_rainy_weather).into(imageView);
        } else if (weatherCode / 100 == 5) {
            Glide.with(context).load(R.drawable.ic_rainy_weather).into(imageView);
        } else if (weatherCode / 100 == 6) {
            Glide.with(context).load(R.drawable.ic_snow_weather).into(imageView);
        } else if (weatherCode / 100 == 7) {
            Glide.with(context).load(R.drawable.ic_unknown).into(imageView);
        } else if (weatherCode == 800) {
            Glide.with(context).load(R.drawable.ic_clear_day).into(imageView);
        } else if (weatherCode == 801) {
            Glide.with(context).load(R.drawable.ic_few_clouds).into(imageView);
        } else if (weatherCode == 803) {
            Glide.with(context).load(R.drawable.ic_broken_clouds).into(imageView);
        } else if (weatherCode / 100 == 8) {
            Glide.with(context).load(R.drawable.ic_cloudy_weather).into(imageView);
        }
    }

    public static String getWeatherStatus(int weatherCode, boolean isRTL) {
        if (weatherCode / 100 == 2) {
            if (isRTL) {
                return Constants.WEATHER_STATUS_PERSIAN[0];
            } else {
                return Constants.WEATHER_STATUS[0];
            }
        } else if (weatherCode / 100 == 3) {
            if (isRTL) {
                return Constants.WEATHER_STATUS_PERSIAN[1];
            } else {
                return Constants.WEATHER_STATUS[1];
            }
        } else if (weatherCode / 100 == 5) {
            if (isRTL) {
                return Constants.WEATHER_STATUS_PERSIAN[2];
            } else {
                return Constants.WEATHER_STATUS[2];
            }
        } else if (weatherCode / 100 == 6) {
            if (isRTL) {
                return Constants.WEATHER_STATUS_PERSIAN[3];
            } else {
                return Constants.WEATHER_STATUS[3];
            }
        } else if (weatherCode / 100 == 7) {
            if (isRTL) {
                return Constants.WEATHER_STATUS_PERSIAN[4];
            } else {
                return Constants.WEATHER_STATUS[4];
            }
        } else if (weatherCode == 800) {
            if (isRTL) {
                return Constants.WEATHER_STATUS_PERSIAN[5];
            } else {
                return Constants.WEATHER_STATUS[5];
            }
        } else if (weatherCode == 801) {
            if (isRTL) {
                return Constants.WEATHER_STATUS_PERSIAN[6];
            } else {
                return Constants.WEATHER_STATUS[6];
            }
        } else if (weatherCode == 803) {
            if (isRTL) {
                return Constants.WEATHER_STATUS_PERSIAN[7];
            } else {
                return Constants.WEATHER_STATUS[7];
            }
        } else if (weatherCode / 100 == 8) {
            if (isRTL) {
                return Constants.WEATHER_STATUS_PERSIAN[8];
            } else {
                return Constants.WEATHER_STATUS[8];
            }
        }
        if (isRTL) {
            return Constants.WEATHER_STATUS_PERSIAN[4];
        } else {
            return Constants.WEATHER_STATUS[4];
        }
    }

    public static int getWeatherAnimation(int weatherCode) {
        if (weatherCode / 100 == 2) {
            return R.raw.storm_weather;
        } else if (weatherCode / 100 == 3) {
            return R.raw.raniny_weather;
        } else if (weatherCode / 100 == 5) {
            return R.raw.raniny_weather;
        } else if (weatherCode / 100 == 6) {
            return R.raw.snow_weather;
        } else if (weatherCode / 100 == 7) {
            return R.raw.unknown;
        } else if (weatherCode == 800) {
            return R.raw.clear_day;
        } else if (weatherCode == 801) {
            return R.raw.few_clouds;
        } else if (weatherCode == 803) {
            return R.raw.broken_clouds;
        } else if (weatherCode / 100 == 8) {
            return R.raw.cloudy_weather;
        }
        return R.raw.unknown;
    }

    public static boolean isRTL(Context context) {
        Locale locale = ConfigurationCompat.getLocales(context.getResources().getConfiguration()).get(0);
        final int directionality = Character.getDirectionality(locale.getDisplayName().charAt(0));
        return directionality == Character.DIRECTIONALITY_RIGHT_TO_LEFT ||
                directionality == Character.DIRECTIONALITY_RIGHT_TO_LEFT_ARABIC;
    }


    public static boolean isTimePass(long lastStored) {
        return System.currentTimeMillis() - lastStored > Constants.TIME_TO_PASS;
    }

}

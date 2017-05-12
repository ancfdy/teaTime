package com.teatime.UI.View;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.DatePicker;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.teatime.R;
import com.teatime.UI.Adapter.PopupWindowAdapter;
import com.teatime.Utils.TeaUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


/**
 * 对话框封装类
 * 
 * @author jiqinlin
 * 
 */
public class DialogTools {

	/**
	 * 创建普通对话框
	 * 
	 * @param ctx
	 *            上下文 必填
	 * @param iconId
	 *            图标，如：R.drawable.icon 必填
	 * @param title
	 *            标题 必填
	 * @param message
	 *            显示内容 必填
	 * @param btnName
	 *            按钮名称 必填
	 * @param listener
	 *            监听器，需实现android.content.DialogInterface.OnClickListener接口 必填
	 * @return
	 */
	public static Dialog createNormalDialog(Context ctx, int iconId,
			String title, String message, String btnName,
			OnClickListener listener) {
		Dialog dialog = null;
		android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(
				ctx);
		// 设置对话框的图标
		builder.setIcon(iconId);
		// 设置对话框的标题
		builder.setTitle(title);
		// 设置对话框的显示内容
		builder.setMessage(message);
		// 添加按钮，android.content.DialogInterface.OnClickListener.OnClickListener
		builder.setPositiveButton(btnName, listener);
		// 创建一个普通对话框
		dialog = builder.create();
		return dialog;
	}

	/**
	 * 创建列表对话框
	 * 
	 * @param ctx
	 *            上下文 必填
	 * @param iconId
	 *            图标，如：R.drawable.icon 必填
	 * @param title
	 *            标题 必填
	 * @param itemsId
	 *            字符串数组资源id 必填
	 * @param listener
	 *            监听器，需实现android.content.DialogInterface.OnClickListener接口 必填
	 * @return
	 */
	public static Dialog createListDialog(Context ctx, int iconId,
			String title, int itemsId, OnClickListener listener) {
		Dialog dialog = null;
		android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(
				ctx);
		// 设置对话框的图标
		builder.setIcon(iconId);
		// 设置对话框的标题
		builder.setTitle(title);
		// 添加按钮，android.content.DialogInterface.OnClickListener.OnClickListener
		builder.setItems(itemsId, listener);
		// 创建一个列表对话框
		dialog = builder.create();
		return dialog;
	}

	/**
	 * 创建单选按钮对话框
	 * 
	 * @param ctx
	 *            上下文 必填
	 * @param iconId
	 *            图标，如：R.drawable.icon 必填
	 * @param title
	 *            标题 必填
	 * @param itemsId
	 *            字符串数组资源id 必填
	 * @param listener
	 *            单选按钮项监听器，需实现android.content.DialogInterface.OnClickListener接口
	 *            必填
	 * @param btnName
	 *            按钮名称 必填
	 * @param listener2
	 *            按钮监听器，需实现android.content.DialogInterface.OnClickListener接口 必填
	 * @return
	 */
	public static Dialog createRadioDialog(Context ctx, int iconId,
			String title, int itemsId, OnClickListener listener,
			String btnName, OnClickListener listener2) {
		Dialog dialog = null;
		android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(
				ctx);
		// 设置对话框的图标
		builder.setIcon(iconId);
		// 设置对话框的标题
		builder.setTitle(title);
		// 0: 默认第一个单选按钮被选中
		builder.setSingleChoiceItems(itemsId, 0, listener);
		// 添加一个按钮
		builder.setPositiveButton(btnName, listener2);
		// 创建一个单选按钮对话框
		dialog = builder.create();
		return dialog;
	}

	/**
	 * 创建复选对话框
	 * 
	 * @param ctx
	 *            上下文 必填
	 * @param iconId
	 *            图标，如：R.drawable.icon 必填
	 * @param title
	 *            标题 必填
	 * @param itemsId
	 *            字符串数组资源id 必填
	 * @param flags
	 *            初始复选情况 必填
	 * @param listener
	 *            单选按钮项监听器，需实现android.content.DialogInterface.
	 *            OnMultiChoiceClickListener接口 必填
	 * @param btnName
	 *            按钮名称 必填
	 * @param listener2
	 *            按钮监听器，需实现android.content.DialogInterface.OnClickListener接口 必填
	 * @return
	 */
	public static Dialog createCheckBoxDialog(
			Context ctx,
			int iconId,
			String title,
			int itemsId,
			boolean[] flags,
			DialogInterface.OnMultiChoiceClickListener listener,
			String btnName, OnClickListener listener2) {
		Dialog dialog = null;
		android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(
				ctx);
		// 设置对话框的图标
		builder.setIcon(iconId);
		// 设置对话框的标题
		builder.setTitle(title);
		builder.setMultiChoiceItems(itemsId, flags, listener);
		// 添加一个按钮
		builder.setPositiveButton(btnName, listener2);
		// 创建一个复选对话框
		dialog = builder.create();
		return dialog;
	}

	/**
	 * 创建时间设置对话框
	 * 
	 * @param ctx
	 *            上下文 必填
	 * @param iconId
	 *            图标，如：R.drawable.icon 必填
	 * @param title
	 *            标题 必填
	 * @param view
	 *            自定义Layout 必填
	 * @param btnOk
	 *            按钮名称 必填
	 * @param btnCancel
	 *            按钮名称 必填
	 * @param listener
	 *            按钮监听器，需实现android.content.DialogInterface. setPositiveButton接口
	 *            必填
	 * @param listener2
	 *            按钮监听器，需实现android.content.DialogInterface.setNegativeButton接口
	 *            必填
	 * @return
	 */
	public static Dialog createSetDateDialog(Context ctx, int iconId,
			String title, View view, String btnOk, String btnCancel,
			OnClickListener listener,
			OnClickListener listener2) {
		Dialog dialog = null;
		android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(
				ctx);
		// 设置对话框的图标
		builder.setIcon(iconId);
		// 设置对话框的标题
		builder.setTitle(title);
		// 设置view界面
		builder.setView(view);
		// 添加一个按钮
		builder.setPositiveButton(btnOk, listener);
		// 添加一个按钮
		builder.setNegativeButton(btnCancel, listener2);
		// 创建一个时间对话框
		dialog = builder.create();
		return dialog;
	}

	/*
	 * 显示普通对话框
	 */
	public static void showNormalDialog(Context context, final Handler handle,
			String title, String data) {
		Dialog mDialog;
		mDialog = DialogTools.createNormalDialog(context, R.drawable.icon,
				title, data, " 确 定 ",
				new OnClickListener() {
					@Override
					public void onClick(DialogInterface mDialog, int which) {
						Message msg2 = new Message();
						msg2.what = 1000;
						msg2.obj = "创建普通对话框";
						handle.sendMessage(msg2);
						return;
					}
				});
		mDialog.show();
	}

	// /*
	// * 设置日期
	// */
	// public void createSetDateDialog() {
	// Calendar calendar = null; // 通过Calendar获取系统时间
	// calendar = Calendar.getInstance();
	// View view = (LinearLayout)
	// getActivity().getLayoutInflater().inflate(R.layout.date_dialog, null);
	// final DatePicker datePicker = (DatePicker)
	// view.findViewById(R.id.date_picker);
	// // 设置日期简略显示 否则详细显示 包括:星期周
	// datePicker.setCalendarViewShown(false);
	// // 初始化当前日期
	// calendar.setTimeInMillis(System.currentTimeMillis());
	// datePicker.init(calendar.get(Calendar.YEAR),
	// calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
	// null);
	// mDialog = DialogTool.createSetDateDialog(getActivity(), R.drawable.icon,
	// "设置日期", view, "确定", "取消",
	// new android.content.DialogInterface.OnClickListener() {
	// @Override
	// public void onClick(DialogInterface mDialog, int which) {
	// // 日期格式
	// StringBuffer sb = new StringBuffer();
	// sb.append(String.format("%d-%02d-%02d", datePicker.getYear(),
	// datePicker.getMonth() + 1,
	// datePicker.getDayOfMonth()));
	// // 赋值后面闹钟使用
	// Message msg3 = new Message();
	// msg3.what = 0;
	// msg3.obj = sb;
	// handle.sendMessage(msg3);
	// return;
	// }
	// }, new android.content.DialogInterface.OnClickListener() {
	// @Override
	// public void onClick(DialogInterface mDialog, int which) {
	// mDialog.cancel();
	// }
	// });
	// mDialog.show();
	// }
	public static void showDatePickerDialog1(Context context,
			final Handler handle, final int i) {
		// 普通按钮事件
		Calendar d = Calendar.getInstance(Locale.CHINA);
		// 创建一个日历引用d，通过静态方法getInstance() 从指定时区 Locale.CHINA 获得一个日期实例
		Date myDate = new Date();
		// 创建一个Date实例
		d.setTime(myDate);
		// 设置日历的时间，把一个新建Date实例myDate传入
		int year = d.get(Calendar.YEAR);
		int month = d.get(Calendar.MONTH);
		int day = d.get(Calendar.DAY_OF_MONTH);
		// 获得日历中的 year month day
		DatePickerDialog dlg = new DatePickerDialog(context,
				new OnDateSetListener() {

					@Override
					public void onDateSet(DatePicker view, int year,
							int monthOfYear, int dayOfMonth) {
						// DatePickerDialog 中按钮Set按下时自动调用
						// 通过id获得TextView对象
						Message msg1 = new Message();

						msg1.what = i;
						msg1.obj = Integer.toString(year) + "-"
								+ Integer.toString(monthOfYear + 1) + "-"
								+ Integer.toString(dayOfMonth);
						handle.sendMessage(msg1);

					}
				}, year, month, day);
		// 新建一个DatePickerDialog 构造方法中
		// （设备上下文，OnDateSetListener时间设置监听器，默认年，默认月，默认日）
		dlg.show();
		// 让DatePickerDialog显示出来
	}

	/**
	 * 弹出日期选择器对话框
	 */
	public static void showDatePickerDialog(Context context,
			final Handler handle) {
		// 普通按钮事件
		Calendar d = Calendar.getInstance(Locale.CHINA);
		// 创建一个日历引用d，通过静态方法getInstance() 从指定时区 Locale.CHINA 获得一个日期实例
		Date myDate = new Date();
		// 创建一个Date实例
		d.setTime(myDate);
		// 设置日历的时间，把一个新建Date实例myDate传入
		int year = d.get(Calendar.YEAR);
		int month = d.get(Calendar.MONTH);
		int day = d.get(Calendar.DAY_OF_MONTH);
		// 获得日历中的 year month day
		DatePickerDialog dlg = new DatePickerDialog(context,
				new OnDateSetListener() {

					@Override
					public void onDateSet(DatePicker view, int year,
							int monthOfYear, int dayOfMonth) {
						// DatePickerDialog 中按钮Set按下时自动调用
						// 通过id获得TextView对象
						Message msg1 = new Message();

						msg1.what = 1000;
						msg1.obj = Integer.toString(year) + "-"
								+ Integer.toString(monthOfYear + 1) + "-"
								+ Integer.toString(dayOfMonth);
						handle.sendMessage(msg1);

					}
				}, year, month, day);
		// 新建一个DatePickerDialog 构造方法中
		// （设备上下文，OnDateSetListener时间设置监听器，默认年，默认月，默认日）
		dlg.show();
		// 让DatePickerDialog显示出来
	}

	/**
	 * 弹出日期选择器对话框
	 */
	public static void showDatePickerDialog(Context context,
			final Handler handle, final int flag) {
		// 普通按钮事件
		Calendar d = Calendar.getInstance(Locale.CHINA);
		// 创建一个日历引用d，通过静态方法getInstance() 从指定时区 Locale.CHINA 获得一个日期实例
		Date myDate = new Date();
		// 创建一个Date实例
		d.setTime(myDate);
		// 设置日历的时间，把一个新建Date实例myDate传入
		int year = d.get(Calendar.YEAR);
		int month = d.get(Calendar.MONTH);
		int day = d.get(Calendar.DAY_OF_MONTH);
		// 获得日历中的 year month day
		DatePickerDialog dlg = new DatePickerDialog(context,
				new OnDateSetListener() {

					@Override
					public void onDateSet(DatePicker view, int year,
							int monthOfYear, int dayOfMonth) {
						// DatePickerDialog 中按钮Set按下时自动调用
						// 通过id获得TextView对象
						Message msg1 = new Message();

						msg1.what = 1000 - flag;
						String mouth = Integer.toString(monthOfYear + 1);
						String day = Integer.toString(dayOfMonth);
						if (mouth.length() < 2)
							mouth = "0" + mouth;
						if (day.length() < 2)
							day = "0" + day;
						msg1.obj = Integer.toString(year) + "-" + mouth + "-"
								+ day;
						handle.sendMessage(msg1);

					}
				}, year, month, day);
		// 新建一个DatePickerDialog 构造方法中
		// （设备上下文，OnDateSetListener时间设置监听器，默认年，默认月，默认日）
		// 让DatePickerDialog显示出来
		dlg.show();

	}

	/**
	 * 应用界面的日期选择器 只显示年月
	 * 
	 * @param context
	 * @param handle
	 * @param flag
	 */
	public static void datePickerDialog(Context context, final Handler handle,
			final int flag) {
		Calendar c = Calendar.getInstance(Locale.CHINA);
		new DoubleDatePickerDialog(context, 0,
				new DoubleDatePickerDialog.OnDateSetListener() {

					@Override
					public void onDateSet(DatePicker startDatePicker,
							int startYear, int startMonthOfYear,
							int startDayOfMonth) {
						Message msg1 = new Message();

						msg1.what = 1000 - flag;
						String mouth = Integer.toString(startMonthOfYear + 1);
						String day = Integer.toString(startDayOfMonth);
						if (mouth.length() < 2) {
							mouth = "0" + mouth;
						}
						if (day.length() < 2) {
							day = "0" + day;
						}
						msg1.obj = Integer.toString(startYear) + "-" + mouth + "-" + day;
						handle.sendMessage(msg1);
					}
				}, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), false).show();

	}

	/**
	 * @param context
	 * @param DropDownView
	 *            从那个控件下方开始弹出
	 * @param strRes
	 *            字符串数据源
	 * @param imgRes
	 *            图片数据源
	 * @param listener
	 *            Item点击事件OnItemClickListener
	 */
	public static PopupWindow showPopupWindow(Context context,
			View DropDownView, int[] strRes, int[] imgRes,
			OnItemClickListener listener) {
		int windowsWidth = TeaUtils.getWindowsWidth(context);
		View contentView = LayoutInflater.from(context).inflate(
				R.layout.cf_popup_window, null);
		ListView lv = (ListView) contentView.findViewById(R.id.lv_popup);
		lv.setLayoutParams(new LayoutParams(windowsWidth / 2,
				LayoutParams.WRAP_CONTENT));
		lv.setAdapter(new PopupWindowAdapter(context, strRes, imgRes));
		lv.setOnItemClickListener(listener);
		PopupWindow popupWindow = new PopupWindow(contentView,
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		popupWindow.setBackgroundDrawable(new ColorDrawable(0xff0000));
		popupWindow.setAnimationStyle(-1);
		popupWindow.showAsDropDown(DropDownView, windowsWidth / 2, 18);
		popupWindow.setFocusable(false);
		popupWindow.setOutsideTouchable(true);
		popupWindow.update();
		return popupWindow;
	}
}
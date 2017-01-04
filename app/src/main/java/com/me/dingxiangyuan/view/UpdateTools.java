package com.me.dingxiangyuan.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;

import java.io.File;
import java.io.InputStream;

/**
 *
 * 获取服务器的更新内容
 *
 * 当前版本
 *
 * apk的路径
 *
 * 更新细则
 *
 * @author dagang
 *
 */
public class UpdateTools {
	/*
	 * 用pull解析器解析服务器返回的xml文件 (xml封装了版本号)
	 */
	public static UpdateEntity getUpdataInfo(InputStream is) throws Exception {
		XmlPullParser parser = Xml.newPullParser();
		parser.setInput(is, "utf-8");// 设置解析的数据源
		int type = parser.getEventType();
		UpdateEntity info = new UpdateEntity();// 实体
		while (type != XmlPullParser.END_DOCUMENT) {
			switch (type) {
				case XmlPullParser.START_TAG:
					if ("version".equals(parser.getName())) {
						info.setVersion(parser.nextText()); // 获取版本号
					} else if ("url".equals(parser.getName())) {
						info.setUrl(parser.nextText()); // 获取要升级的APK文件
					} else if ("description".equals(parser.getName())) {
						info.setDescription(parser.nextText()); // 获取该文件的信息
					}
					break;
			}
			type = parser.next();
		}
		return info;
	}

	/**
	 *
	 * 安装Apk
	 *
	 * @param file
	 * @param context
	 */
	public void installApk(File file,Context context) {
		Intent intent = new Intent();
		//执行动作
		intent.setAction(Intent.ACTION_VIEW);
		//执行的数据类型
		intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
		context.startActivity(intent);
	}
}

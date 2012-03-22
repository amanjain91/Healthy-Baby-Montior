/**
 * Copyright (C) 2009, 2010 SC 4ViewSoft SRL
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
package edu.gatech.cic.teams.sudoers;

import java.util.ArrayList;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Paint.Align;

/**
 * Average temperature demo chart.
 */
public class LengthDayChart extends AbstractDemoChart {
	/**
	 * Executes the chart demo.
	 * 
	 * @param context
	 *            the context
	 * @return the built intent
	 */
	public Intent execute(Context context) {
		Cursor c;
		List<double[]> x = new ArrayList<double[]>();
		List<double[]> values = new ArrayList<double[]>();
		SQLiteDatabase db = new DatabaseOpenHelper(context)
				.getReadableDatabase();
		c = db.query("lengthchart",
				new String[] { "Day", "P99", "P01", "P50" }, null, null, null,
				null, null);

		String[] titles = new String[] { "P99", "P0", "P50" };
		double[] p99 = new double[(c.getCount() / 5) + 1];
		double[] p0 = new double[(c.getCount() / 5) + 1];
		double[] p50 = new double[(c.getCount() / 5) + 1];
		double[] days = new double[(c.getCount() / 5) + 1];
		c.moveToFirst();
		int i = 0;
		while (c.moveToNext()) {
			if (i % 5 == 0) {
				p99[i / 5] = c.getDouble(1);
				p0[i / 5] = c.getDouble(2);
				p50[i / 5] = c.getDouble(3);
				days[i / 5] = i;
			}
			i++;
		}
		for (int v = 0; v < titles.length; v++) {
			x.add(days);
		}
		values.add(p99);
		values.add(p0);
		values.add(p50);
		// x.add(new double[] { 22.0 });
		// values.add(new double[] { 34.0 });
		int[] colors = new int[] { Color.CYAN, Color.GREEN, Color.RED };
		PointStyle[] styles = new PointStyle[] { PointStyle.POINT,
				PointStyle.POINT, PointStyle.POINT };
		XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
		int length = renderer.getSeriesRendererCount();
		for (i = 0; i < length; i++) {
			((XYSeriesRenderer) renderer.getSeriesRendererAt(i))
					.setFillPoints(true);
		}
		setChartSettings(renderer, "Height Day Chart", "Days",
				"Height (in cms)", 0, 1000, 0, 125, Color.LTGRAY, Color.LTGRAY);
		renderer.setXLabels(12);
		renderer.setYLabels(10);
		renderer.setShowGrid(true);
		renderer.setXLabelsAlign(Align.RIGHT);
		renderer.setYLabelsAlign(Align.RIGHT);
		renderer.setZoomButtonsVisible(true);
		renderer.setPanLimits(new double[] { -10, 20, -10, 40 });
		renderer.setZoomLimits(new double[] { -10, 20, -10, 40 });
		Intent intent = ChartFactory.getLineChartIntent(context,
				buildDataset(titles, x, values), renderer, "Le Healthy Baby");
		c.close();
		db.close();
		return intent;
	}
}

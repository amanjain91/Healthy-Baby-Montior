/** Licensed under the MIT license: http://www.opensource.org/licenses/mit-license.php */
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

public class HeightChart extends AbstractDemoChart {
	/**
	 * Executes the chart demo.
	 * 
	 * @param context
	 *            the context
	 * @return the built intent
	 */
	public Intent execute(Context context, Child aChild) {
		Cursor c;
		List<double[]> x = new ArrayList<double[]>();
		List<double[]> values = new ArrayList<double[]>();
		SQLiteDatabase db = new DatabaseOpenHelper(context)
				.getReadableDatabase();
		int[] colors;
		PointStyle[] styles;
		c = db.query("lengthchart",
				new String[] { "day", "P99", "P01", "P50" }, null, null, null,
				null, null);
		int factor = 5;
		String[] titles;

		double[] p99 = new double[(c.getCount() / factor) + 1];
		double[] p0 = new double[(c.getCount() / factor) + 1];
		double[] p50 = new double[(c.getCount() / factor) + 1];
		double[] days = new double[(c.getCount() / factor) + 1];
		double[] heightData = new double[c.getCount() / factor + 1];
		titles = new String[] { "P99", "P0", "P50" };

		c.moveToFirst();
		int i = 0;
		int temp;
		do {
			if (i % factor == 0) {
				temp = i / factor;
				p99[temp] = c.getDouble(1);
				p0[temp] = c.getDouble(2);
				p50[temp] = c.getDouble(3);
				days[temp] = i;
			}
			i++;
		} while (c.moveToNext());
		c.close();
		for (i = 0; i < heightData.length; i++) {
			heightData[i] = -5;
		}
		c = db.query(aChild.getDataTableName(),
				new String[] { "Day", "Height" }, null, null, null, null, null);

		i = 0;
		values.add(p99);
		values.add(p0);
		values.add(p50);
		if (c.moveToFirst()) {
			colors = new int[] { Color.CYAN, Color.GREEN, Color.RED, Color.BLUE };
			styles = new PointStyle[] { PointStyle.POINT, PointStyle.POINT,
					PointStyle.POINT, PointStyle.CIRCLE };
			titles = new String[] { "P99", "P0", "P50", "Height" };
			do {
				temp = (int) (c.getDouble(0) / factor);
				heightData[temp] = c.getDouble(1);
				i++;
			} while (c.moveToNext());
			values.add(heightData);
		} else {
			colors = new int[] { Color.CYAN, Color.GREEN, Color.RED };
			styles = new PointStyle[] { PointStyle.POINT, PointStyle.POINT,
					PointStyle.POINT };
		}
		for (int v = 0; v < titles.length; v++) {
			x.add(days);
		}

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
				buildDataset(titles, x, values), renderer, aChild.getName());
		c.close();
		db.close();
		return intent;
	}
}

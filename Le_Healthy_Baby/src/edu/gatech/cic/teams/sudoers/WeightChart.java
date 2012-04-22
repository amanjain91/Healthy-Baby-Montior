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
import android.util.Log;

public class WeightChart extends AbstractDemoChart {
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
		c = db.query("weightchart",
				new String[] { "Day", "P99", "P01", "P50" }, null, null, null,
				null, null);
		int factor = 5;
		String[] titles;
		double[] p99 = new double[(c.getCount() / factor) + 1];
		double[] p0 = new double[(c.getCount() / factor) + 1];
		double[] p50 = new double[(c.getCount() / factor) + 1];
		double[] days = new double[(c.getCount() / factor) + 1];
		double[] weightData = new double[c.getCount() / factor + 1];
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

		// initializing height to -5
		for (i = 0; i < weightData.length; i++) {
			weightData[i] = -5;
		}
		c = db.query(aChild.getDataTableName(),
				new String[] { "Day", "Weight" }, null, null, null, null, null);

		i = 0;
		int[] colors;
		PointStyle[] styles;
		if (c.moveToFirst()) {
			titles = new String[] { "P99", "P0", "P50", "Weight" };
			styles = new PointStyle[] { PointStyle.POINT, PointStyle.POINT,
					PointStyle.POINT, PointStyle.CIRCLE };
			colors = new int[] { Color.CYAN, Color.GREEN, Color.RED, Color.BLUE };
			values.add(weightData);
			do {
				temp = (int) (c.getDouble(0) / factor);
				weightData[temp] = c.getDouble(1);
				Log.v("WeightChart", "Got a double: " + weightData[temp]);
				i++;
			} while (c.moveToNext());
		} else {
			titles = new String[] { "P99", "P0", "P50" };
			styles = new PointStyle[] { PointStyle.POINT, PointStyle.POINT,
					PointStyle.POINT };
			colors = new int[] { Color.CYAN, Color.GREEN, Color.RED, };
		}
		for (int v = 0; v < titles.length; v++) {
			x.add(days);
		}
		values.add(p99);
		values.add(p0);
		values.add(p50);

		XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);

		int length = renderer.getSeriesRendererCount();
		for (i = 0; i < length; i++) {
			((XYSeriesRenderer) renderer.getSeriesRendererAt(i))
					.setFillPoints(true);
		}

		setChartSettings(renderer, "Weight Day Chart", "Days",
				"Weight (in lbs)", 0, 1000, 0, 125, Color.LTGRAY, Color.LTGRAY);
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

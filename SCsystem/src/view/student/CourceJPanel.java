package view.student;

import javax.swing.*;
import java.awt.*;

import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

//**********************************//
//             表格面板类                        //
//**********************************//
public class CourceJPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTable fixedCourceTable;// 固定列表格对象
	private FixedCourceTableModel fixedCourceTableModel;// 固定列表格模型对象
	private JTable floatingCourceTable;// 移动列表格对象
	// 移动列表格模型对象
	private FloatingCourceTableModel floatingCourceTableModel;
	private Vector<String> columnNameV;// 表格列名数组
	private Vector<Vector<Object>> tableValueV;// 表格数据数组
	private int fixedColumn = 1;// 固定列数量

	public CourceJPanel(Vector<String> columnNameV, Vector<Vector<Object>> tableValueV, int fixedColumn, int col1,
			int col2, int height) {
		super();
		setLayout(new BorderLayout());
		this.columnNameV = columnNameV;
		this.tableValueV = tableValueV;
		this.fixedColumn = fixedColumn;
		// 创建固定列表格模型对象
		fixedCourceTableModel = new FixedCourceTableModel();

		// 创建固定列表格对象
		fixedCourceTable = new JTable(fixedCourceTableModel);
		fixedCourceTable.setRowHeight(height);

		// 设置居中显示
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		fixedCourceTable.setDefaultRenderer(Object.class, renderer);

		// fixedCourceTable.getColumns(xx).setPreferredWidth(80);
		// 获得选择模型对象
		ListSelectionModel fixed = fixedCourceTable.getSelectionModel();
		// 选择模式为单选
		fixed.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// 添加行被选中的事件监听器
		fixed.addListSelectionListener(new MListSelectionListener(true));

		// 创建可移动列表格模型对象
		floatingCourceTableModel = new FloatingCourceTableModel();
		// 创建可移动列表格对象
		floatingCourceTable = new JTable(floatingCourceTableModel);
		floatingCourceTable.setRowHeight(height);

		// 设置单元格宽度
		for (int i = 1; i < columnNameV.size(); i++) {
			setColumnWidth(floatingCourceTable, columnNameV.get(i), col2);
		}
		// 设置居中显示
		renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		floatingCourceTable.setDefaultRenderer(Object.class, renderer);

		// 关闭表格的自动调整功能
		floatingCourceTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		ListSelectionModel floating = floatingCourceTable.getSelectionModel();// 获得选择模型对象
		// 选择模式为单选
		floating.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// 添加行被选中的事件监听器
		MListSelectionListener listener = new MListSelectionListener(false);
		floating.addListSelectionListener(listener);

		JScrollPane scrollPane = new JScrollPane();// 创建一个滚动面版对象
		// 将固定列表格头放到滚动面版的左上方
		scrollPane.setCorner(JScrollPane.UPPER_LEFT_CORNER, fixedCourceTable.getTableHeader());

		// 创建一个用来显示基础信息的视口对象
		JViewport viewport = new JViewport();
		viewport.setView(fixedCourceTable);// 将固定列表格添加到视口中
		// 设置视口的首选大小为固定列表格的首选大小
		viewport.setPreferredSize(fixedCourceTable.getPreferredSize());

		// 将视口添加到滚动面版的标题视口中
		scrollPane.setRowHeaderView(viewport);
		// 将可移动表格添加到默认视口
		scrollPane.setViewportView(floatingCourceTable);
		add(scrollPane, BorderLayout.CENTER);
	}

	public void setColumnWidth(JTable table, Object colname, int width) {
		table.getColumn(colname).setPreferredWidth(width);
	}

	private class FixedCourceTableModel extends AbstractTableModel {
		private static final long serialVersionUID = 1L;

		public int getColumnCount() {// 返回固定列的数量
			return fixedColumn;
		}

		public int getRowCount() {// 返回行数
			return tableValueV.size();
		}

		// 返回指定单元格的值
		public Object getValueAt(int rowIndex, int columnIndex) {
			return tableValueV.get(rowIndex).get(columnIndex);
		}

		@Override
		public String getColumnName(int columnIndex) {// 返回指定列的名称
			return columnNameV.get(columnIndex);
		}
	}

	private class FloatingCourceTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 1L;

		public int getColumnCount() {// 返回可移动列的数量
			return columnNameV.size() - fixedColumn;// 需要扣除固定列的数量
		}

		public int getRowCount() {// 返回行数
			return tableValueV.size();
		}

		// 返回指定单元格的值
		public Object getValueAt(int rowIndex, int columnIndex) {
			// 需要为列索引加上固定列的数量
			return tableValueV.get(rowIndex).get(columnIndex + fixedColumn);
		}

		@Override
		public String getColumnName(int columnIndex) {// 返回指定列的名称
			// 需要为列索引加上固定列的数量
			return columnNameV.get(columnIndex + fixedColumn);
		}
	}

	private class MListSelectionListener implements ListSelectionListener {
		boolean isFixedColumnTable = true; // 默认由选中固定列表格中的行触发

		public MListSelectionListener(boolean isFixedColumnTable) {
			this.isFixedColumnTable = isFixedColumnTable;
		}

		public void valueChanged(ListSelectionEvent e) {
			if (isFixedColumnTable) { // 由选中固定列表格中的行触发
				// 获得固定列表格中的选中行
				int row = fixedCourceTable.getSelectedRow();
				// 同时选中右侧可移动列表格中的相应行
				floatingCourceTable.setRowSelectionInterval(row, row);
			} else { // 由选中可移动列表格中的行触发
				// 获得可移动列表格中的选中行
				int row = floatingCourceTable.getSelectedRow();
				// 同时选中左侧固定列表格中的相应行
				fixedCourceTable.setRowSelectionInterval(row, row);
			}
		}
	}
}

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
//             ��������                        //
//**********************************//
public class CourceJPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTable fixedCourceTable;// �̶��б�����
	private FixedCourceTableModel fixedCourceTableModel;// �̶��б��ģ�Ͷ���
	private JTable floatingCourceTable;// �ƶ��б�����
	// �ƶ��б��ģ�Ͷ���
	private FloatingCourceTableModel floatingCourceTableModel;
	private Vector<String> columnNameV;// �����������
	private Vector<Vector<Object>> tableValueV;// �����������
	private int fixedColumn = 1;// �̶�������

	public CourceJPanel(Vector<String> columnNameV, Vector<Vector<Object>> tableValueV, int fixedColumn, int col1,
			int col2, int height) {
		super();
		setLayout(new BorderLayout());
		this.columnNameV = columnNameV;
		this.tableValueV = tableValueV;
		this.fixedColumn = fixedColumn;
		// �����̶��б��ģ�Ͷ���
		fixedCourceTableModel = new FixedCourceTableModel();

		// �����̶��б�����
		fixedCourceTable = new JTable(fixedCourceTableModel);
		fixedCourceTable.setRowHeight(height);

		// ���þ�����ʾ
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		fixedCourceTable.setDefaultRenderer(Object.class, renderer);

		// fixedCourceTable.getColumns(xx).setPreferredWidth(80);
		// ���ѡ��ģ�Ͷ���
		ListSelectionModel fixed = fixedCourceTable.getSelectionModel();
		// ѡ��ģʽΪ��ѡ
		fixed.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// ����б�ѡ�е��¼�������
		fixed.addListSelectionListener(new MListSelectionListener(true));

		// �������ƶ��б��ģ�Ͷ���
		floatingCourceTableModel = new FloatingCourceTableModel();
		// �������ƶ��б�����
		floatingCourceTable = new JTable(floatingCourceTableModel);
		floatingCourceTable.setRowHeight(height);

		// ���õ�Ԫ����
		for (int i = 1; i < columnNameV.size(); i++) {
			setColumnWidth(floatingCourceTable, columnNameV.get(i), col2);
		}
		// ���þ�����ʾ
		renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		floatingCourceTable.setDefaultRenderer(Object.class, renderer);

		// �رձ����Զ���������
		floatingCourceTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		ListSelectionModel floating = floatingCourceTable.getSelectionModel();// ���ѡ��ģ�Ͷ���
		// ѡ��ģʽΪ��ѡ
		floating.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// ����б�ѡ�е��¼�������
		MListSelectionListener listener = new MListSelectionListener(false);
		floating.addListSelectionListener(listener);

		JScrollPane scrollPane = new JScrollPane();// ����һ������������
		// ���̶��б��ͷ�ŵ������������Ϸ�
		scrollPane.setCorner(JScrollPane.UPPER_LEFT_CORNER, fixedCourceTable.getTableHeader());

		// ����һ��������ʾ������Ϣ���ӿڶ���
		JViewport viewport = new JViewport();
		viewport.setView(fixedCourceTable);// ���̶��б����ӵ��ӿ���
		// �����ӿڵ���ѡ��СΪ�̶��б�����ѡ��С
		viewport.setPreferredSize(fixedCourceTable.getPreferredSize());

		// ���ӿ���ӵ��������ı����ӿ���
		scrollPane.setRowHeaderView(viewport);
		// �����ƶ������ӵ�Ĭ���ӿ�
		scrollPane.setViewportView(floatingCourceTable);
		add(scrollPane, BorderLayout.CENTER);
	}

	public void setColumnWidth(JTable table, Object colname, int width) {
		table.getColumn(colname).setPreferredWidth(width);
	}

	private class FixedCourceTableModel extends AbstractTableModel {
		private static final long serialVersionUID = 1L;

		public int getColumnCount() {// ���ع̶��е�����
			return fixedColumn;
		}

		public int getRowCount() {// ��������
			return tableValueV.size();
		}

		// ����ָ����Ԫ���ֵ
		public Object getValueAt(int rowIndex, int columnIndex) {
			return tableValueV.get(rowIndex).get(columnIndex);
		}

		@Override
		public String getColumnName(int columnIndex) {// ����ָ���е�����
			return columnNameV.get(columnIndex);
		}
	}

	private class FloatingCourceTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 1L;

		public int getColumnCount() {// ���ؿ��ƶ��е�����
			return columnNameV.size() - fixedColumn;// ��Ҫ�۳��̶��е�����
		}

		public int getRowCount() {// ��������
			return tableValueV.size();
		}

		// ����ָ����Ԫ���ֵ
		public Object getValueAt(int rowIndex, int columnIndex) {
			// ��ҪΪ���������Ϲ̶��е�����
			return tableValueV.get(rowIndex).get(columnIndex + fixedColumn);
		}

		@Override
		public String getColumnName(int columnIndex) {// ����ָ���е�����
			// ��ҪΪ���������Ϲ̶��е�����
			return columnNameV.get(columnIndex + fixedColumn);
		}
	}

	private class MListSelectionListener implements ListSelectionListener {
		boolean isFixedColumnTable = true; // Ĭ����ѡ�й̶��б���е��д���

		public MListSelectionListener(boolean isFixedColumnTable) {
			this.isFixedColumnTable = isFixedColumnTable;
		}

		public void valueChanged(ListSelectionEvent e) {
			if (isFixedColumnTable) { // ��ѡ�й̶��б���е��д���
				// ��ù̶��б���е�ѡ����
				int row = fixedCourceTable.getSelectedRow();
				// ͬʱѡ���Ҳ���ƶ��б���е���Ӧ��
				floatingCourceTable.setRowSelectionInterval(row, row);
			} else { // ��ѡ�п��ƶ��б���е��д���
				// ��ÿ��ƶ��б���е�ѡ����
				int row = floatingCourceTable.getSelectedRow();
				// ͬʱѡ�����̶��б���е���Ӧ��
				fixedCourceTable.setRowSelectionInterval(row, row);
			}
		}
	}
}

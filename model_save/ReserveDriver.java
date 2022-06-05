package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ReserveDriver {

	public static void main(String[] args) throws IdealException {
		// TODO 自動生成されたメソッド・スタブ

		SimpleDateFormat sdf = new SimpleDateFormat(Reserve.DATE_FORMAT);

		//Reserve r = new Reserve();
		//r.setCourseId(1);
		//r.setPerson(5);
		//r.setRsvDd(10);
		//r.setRsvHh(18);
		//r.setRsvMi(20);
		//r.setRsvMm(5);
		//r.setRsvYy(2021);
		//r.setTableId(7);
		//r.setUsrId(1);

		//Reserve.insert(r);

		ArrayList<Reserve> list = Reserve.getReserveList(1);
		list.forEach(s->System.out.println(s.getAppDate()));
		System.out.println(Reserve.getReserve(list.get(4).getRsvId()).getPerson());
		TableLoc t2 =Reserve.updateChk(Reserve.getReserve(list.get(4).getRsvId()).getPerson(), Reserve.getReserve(list.get(4).getRsvId()).getRsvDateString(), 4);
		TableLoc t3 =Reserve.updateChk(2, Reserve.getReserve(list.get(4).getRsvId()).getRsvDateString(), 5);
		list.get(0).setCourseId(3);
		Reserve.update(list.get(0));
		//Reserve.delete(list.get(1));

		TableLoc tl = Reserve.insertChk(list.get(4).getRsvDateString(),5);
		if(tl != null){
			System.out.println(tl.getTableName());
		}else {
			System.out.println("未発見です");
		}

		if(t2 != null){
			System.out.println(t2.getTableName());
		}else {
			System.out.println("未発見です");
		}

		if(t3 != null){
			System.out.println(t3.getTableName());
		}else {
			System.out.println("未発見です");
		}

	}

}

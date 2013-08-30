function CalConv() {
	FIRSTYEAR = 1998;
	LASTYEAR = 2031;
	today = new Date();
	SolarYear = today.getFullYear();
	SolarMonth = today.getMonth() + 1;
	SolarDate = today.getDate();
	Weekday = today.getDay();
	LunarCal = [
			new tagLunarCal(27, 5, 3, 43, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 1),
			new tagLunarCal(46, 0, 4, 48, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0, 1),
			new tagLunarCal(35, 0, 5, 53, 1, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1),
			new tagLunarCal(23, 4, 0, 59, 1, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1),
			new tagLunarCal(42, 0, 1, 4, 1, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1),
			new tagLunarCal(31, 0, 2, 9, 1, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 0),
			new tagLunarCal(21, 2, 3, 14, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1),
			new tagLunarCal(39, 0, 5, 20, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1),
			new tagLunarCal(28, 7, 6, 25, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 1),
			new tagLunarCal(48, 0, 0, 30, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0, 1, 1),
			new tagLunarCal(37, 0, 1, 35, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1),
			new tagLunarCal(25, 5, 3, 41, 1, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1),
			new tagLunarCal(44, 0, 4, 46, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1),
			new tagLunarCal(33, 0, 5, 51, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1),
			new tagLunarCal(22, 4, 6, 56, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0),
			new tagLunarCal(40, 0, 1, 2, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0),
			new tagLunarCal(30, 9, 2, 7, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1),
			new tagLunarCal(49, 0, 3, 12, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1),
			new tagLunarCal(38, 0, 4, 17, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1, 0),
			new tagLunarCal(27, 6, 6, 23, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1, 1),
			new tagLunarCal(46, 0, 0, 28, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1, 0),
			new tagLunarCal(35, 0, 1, 33, 0, 1, 1, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0),
			new tagLunarCal(24, 4, 2, 38, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1),
			new tagLunarCal(42, 0, 4, 44, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1),
			new tagLunarCal(31, 0, 5, 49, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0),
			new tagLunarCal(21, 2, 6, 54, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1),
			new tagLunarCal(40, 0, 0, 59, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1),
			new tagLunarCal(28, 6, 2, 5, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1, 1, 0),
			new tagLunarCal(47, 0, 3, 10, 1, 0, 1, 0, 0, 1, 0, 0, 1, 1, 1, 0, 1),
			new tagLunarCal(36, 0, 4, 15, 1, 1, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1),
			new tagLunarCal(25, 5, 5, 20, 1, 1, 1, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0),
			new tagLunarCal(43, 0, 0, 26, 1, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1),
			new tagLunarCal(32, 0, 1, 31, 1, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 0),
			new tagLunarCal(22, 3, 2, 36, 0, 1, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0) ];
	SolarCal = [ 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 ];
	SolarDays = [ 0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334, 365,
			396, 0, 31, 60, 91, 121, 152, 182, 213, 244, 274, 305, 335, 366,
			397 ];
	if (SolarYear <= FIRSTYEAR || SolarYear > LASTYEAR) {
		return 1;
	}
	sm = SolarMonth - 1;
	if (sm < 0 || sm > 11) {
		return 2;
	}
	leap = GetLeap(SolarYear);
	if (sm == 1) {
		c = leap + 28;
	} else {
		c = SolarCal[sm];
	}
	if (SolarDate < 1 || SolarDate > c) {
		return 3;
	}
	y = SolarYear - FIRSTYEAR;
	acc = SolarDays[leap * 14 + sm] + SolarDate;
	kc = acc + LunarCal[y].BaseKanChih;
	Kan = kc % 10;
	Chih = kc % 12;
	Age = kc % 60;
	if (Age < 22) {
		Age = 22 - Age;
	} else {
		Age = 82 - Age;
	}
	if (acc <= LunarCal[y].BaseDays) {
		y--;
		LunarYear = SolarYear - 1;
		leap = GetLeap(LunarYear);
		sm += 12;
		acc = SolarDays[leap * 14 + sm] + SolarDate;
	} else {
		LunarYear = SolarYear;
	}
	l1 = LunarCal[y].BaseDays;
	for (i = 0; i < 13; i++) {
		l2 = l1 + LunarCal[y].MonthDays[i] + 29;
		if (acc <= l2) {
			break;
		}
		l1 = l2;
	}
	LunarMonth = i + 1;
	LunarDate = acc - l1;
	im = LunarCal[y].Intercalation;
	if (im != 0 && LunarMonth > im) {
		LunarMonth--;
		if (LunarMonth == im) {
			LunarMonth = -im;
		}
	}
	if (LunarMonth > 12) {
		LunarMonth -= 12;
	}
	today = new Date();
	function b() {
		this.length = b.arguments.length;
		for ( var d = 0; d < this.length; d++) {
			this[d + 1] = b.arguments[d];
		}
	}
	var c = new b("星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六");
	months = [ "一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二" ];
	days = [ "初一", "初二", "初三", "初四", "初五", "初六", "初七", "初八", "初九", "初十", "十一",
			"十二", "十三", "十四", " 十五", "十六", "十七", "十八", "十九", "二十", "廿一", "廿二",
			"廿三", "廿四", "廿五", "廿六", "廿七", "廿八", "廿九", "三十" ];
	var a = today.getYear();
	if (a < 2000) {
		a += 1900;
	}
	document.getElementById("nowTime").innerHTML = "今天是 " + a + "年"
			+ (today.getMonth() + 1) + "月" + today.getDate() + "日&nbsp;&nbsp;"
			+ c[today.getDay() + 1] + "&nbsp;&nbsp;农历" + months[LunarMonth - 1]
			+ "月" + days[LunarDate - 1] + "&nbsp";
	document.getElementById("nowTime2").innerHTML = today.toLocaleTimeString();
	return 0;
}
function GetLeap(a) {
	if (a % 400 == 0) {
		return 1;
	} else {
		if (a % 100 == 0) {
			return 0;
		} else {
			if (a % 4 == 0) {
				return 1;
			} else {
				return 0;
			}
		}
	}
}
function tagLunarCal(l, c, p, a, t, s, r, q, o, n, m, j, h, g, f, e, b) {
	this.BaseDays = l;
	this.Intercalation = c;
	this.BaseWeekday = p;
	this.BaseKanChih = a;
	this.MonthDays = [ t, s, r, q, o, n, m, j, h, g, f, e, b ];
}
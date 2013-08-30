var MyPlanTaskView = function() {
	Ext.QuickTips.init();
	return my_calendar();
};
function updateStoreincalendar() {
	var c = prueba.currentView;
	if (c == "month") {
		var e = parseInt(prueba.currentdate.format("m"));
		var d = prueba.currentdate.format("m/d/Y");
		calendarStore.baseParams = {
			action : "month",
			displaymonth : e,
			monthday : d
		};
		calendarStore.reload();
	} else {
		if (c == "day") {
			var b = prueba.currentdate.format("m/d/Y");
			calendarStore.baseParams = {
				action : "day",
				day : b
			};
			calendarStore.reload();
		} else {
			if (c == "week") {
				var b = parseInt(prueba.currentdate.format("W"));
				var a = prueba.getDateRangeOfWeek(b);
				calendarStore.baseParams = {
					action : "week",
					weeknumber : b,
					startweek : a[0].format("m/d/Y"),
					endweek : a[1].format("m/d/Y")
				};
				calendarStore.reload();
			} else {
				if (c == "schedule") {
					if (prueba.viewscheduler.listbody.periodType == 0) {
						var b = prueba.currentdate.format("m/d/Y");
						calendarStore.baseParams = {
							action : "day",
							day : b
						};
						calendarStore.reload();
					} else {
						if (prueba.viewscheduler.listbody.periodType == 1) {
							var b = parseInt(prueba.currentdate.format("W"));
							var a = prueba.getDateRangeOfWeek(b);
							calendarStore.baseParams = {
								action : "week",
								weeknumber : b,
								startweek : a[0].format("m/d/Y"),
								endweek : a[1].format("m/d/Y")
							};
							calendarStore.reload();
						} else {
							if (prueba.viewscheduler.listbody.periodType == 2) {
								var e = parseInt(prueba.currentdate.format("m"));
								var d = prueba.currentdate.format("m/d/Y");
								calendarStore.baseParams = {
									action : "month",
									displaymonth : e,
									monthday : d
								};
								calendarStore.reload();
							} else {
								if (prueba.viewscheduler.listbody.periodType == 3) {
									var a = prueba.viewscheduler
											.getDatesforBimonth(prueba.currentdate);
									calendarStore.baseParams = {
										action : "period",
										start : a[0].format("m/d/Y"),
										ends : a[1].format("m/d/Y")
									};
									calendarStore.reload();
								} else {
									if (prueba.viewscheduler.listbody.periodType == 4) {
										var a = prueba.viewscheduler
												.getDatesforBimonth(prueba.currentdate);
										calendarStore.baseParams = {
											action : "period",
											start : a[0].format("m/d/Y"),
											ends : a[1].format("m/d/Y")
										};
										calendarStore.reload();
									}
								}
							}
						}
					}
				}
			}
		}
	}
}
function my_calendar() {
	var e = new Ext.menu.Item( {
		id : "buttonx1_task",
		iconCls : "x-calendar-month-btnmv_task",
		text : "Custom menu test 1"
	});
	var d = new Ext.menu.Item( {
		id : "buttonx2_task",
		iconCls : "x-calendar-month-btnmv_task",
		text : "Custom menu test 2"
	});
	var g = new Ext.menu.Item( {
		id : "buttonz1_task",
		iconCls : "x-calendar-month-btnmv_task",
		text : "Custom action 1"
	});
	var f = new Ext.menu.Item( {
		id : "buttonz2_task",
		iconCls : "x-calendar-month-btnmv_task",
		text : "Custom action 2"
	});
	var a = new Ext.menu.Item( {
		id : "btnTimerTask",
		iconCls : "task_time",
		text : "Set Task Alarm...."
	});
	var b = new Ext.menu.Item( {
		id : "btnTimerOff",
		iconCls : "task_time_off",
		text : "Delete Task's Alarm...."
	});
	var i = new Ext.menu.Item( {
		id : "buttonx1_task",
		iconCls : "x-calendar-month-btnmv_task",
		text : "Custom menu  on sched test 1"
	});
	var h = new Ext.menu.Item( {
		id : "buttonx2_task",
		iconCls : "x-calendar-month-btnmv_task",
		text : "Custom menu  on sched test 2"
	});
	var c = new Date();
	calendarStore = new Ext.data.Store( {
		url : __ctxPath + "/task/myCalendarPlan.do",
		baseParams : {
			action : "all",
			action : "month",
			displaymonth : 12,
			monthday : c.format("m/d/Y")
		},
		reader : new Ext.data.JsonReader( {
			root : "records",
			id : "id",
			totalProperty : "totalCount"
		}, [ {
			name : "recid",
			mapping : "id",
			type : "string"
		}, {
			name : "subject",
			mapping : "subject",
			type : "string"
		}, {
			name : "description",
			mapping : "description",
			type : "string"
		}, {
			name : "startdate",
			mapping : "startdate",
			type : "string"
		}, {
			name : "enddate",
			mapping : "enddate",
			type : "string"
		}, {
			name : "color",
			mapping : "color",
			type : "string"
		}, {
			name : "parent",
			mapping : "parent",
			type : "int"
		}, {
			name : "priority",
			mapping : "priority",
			type : "int"
		} ])
	});
	prueba = new Ext.ECalendar(
			{
				id : "MyPlanTaskView",
				name : "MyPlanTaskView",
				title : "日程管理",
				mytitle : "",
				height : 500,
				fieldsRefer : {
					id : "recid",
					subject : "subject",
					description : "description",
					color : "color",
					startdate : "startdate",
					enddate : "enddate",
					priority : "priority",
					parent : "parent",
					html : ""
				},
				storeOrderby : "priority",
				storeOrder : "DESC",
				showCal_tbar : true,
				showRefreshbtn : true,
				refreshAction : "view",
				currentView : "month",
				currentdate : c,
				dateSelector : true,
				dateSelectorIcon : __ctxPath
						+ "/ext3/ux/caltask/images/date.png",
				dateSelectorIconCls : "x-cmscalendar-icon-date-selector",
				dateformat : "d/m/Y",
				header : true,
				title : "我的日程管理",
				mytitle : " ",
				iconCls : "x-cmscalendar-icon-main",
				dateSelector : true,
				store : calendarStore,
				monitorBrowserResize : true,
				widgetsBind : {
					bindMonth : null,
					bindDay : null,
					binWeek : null
				},
				tplTaskZoom : new Ext.XTemplate(
						'<tpl for=".">',
						'<div class="ecal-show-basetasktpl-div"><b>主题:</b>{subject}<br>',
						"<b>开始1:</b>{startdate}<br><b>结束1:</b>{enddate}",
						"<br><b>明细:</b><hr>{description}</div>", "</tpl>"),
				iconToday : __ctxPath
						+ "/ext3/ux/caltask/images/cms_calendar.png",
				iconMonthView : __ctxPath
						+ "/ext3/ux/caltask/images/calendar_view_month.png",
				iconWeekView : __ctxPath
						+ "/ext3/ux/caltask/images/calendar_view_week.png",
				iconDayView : __ctxPath
						+ "/ext3/ux/caltask/images/calendar_view_day.png",
				iconSchedView : __ctxPath
						+ "/ext3/ux/caltask/images/calendar_view_schedule.png",
				iconRefresh : __ctxPath
						+ "/ext3/ux/caltask/images/calendar_view_refresh.png",
				iconAddTask : __ctxPath
						+ "/ext3/ux/caltask/images/calendar_view_addTask.png",
				loadMask : true,
				customMaskText : "系统信息<br>请稍等!<br>正在处理日程的信息",
				sview : {
					header : true,
					headerFormat : "Y-M",
					headerButtons : true,
					headerAction : "event",
					periodselector : false,
					blankzonebg : "#6C90B4",
					blankHTML : '<div id="{calx}-test-img" class="custom_image_addNewEvent_scheduler" style=" width:100%; background-color:#6C90B4"><div align="center" id="{sched_addevent_id}"><img src="' + __ctxPath + '/ext3/ux/caltask/images/no_events_default.jpg" width="174" height="143"></div><div class="custom_text_addNewEvent_scheduler">点击图片加日程任务</div></div>',
					listItems : {
						headerTitle : "日程任务",
						periodFormats : {
							Day : "星期l - d - F - Y",
							DayScheduler_format : "d",
							hourFormat : "h:i a",
							startTime : "7:00:00 am",
							endTime : "10:00:00 pm",
							WeekTPL : '<tpl for=".">Week No.{numweek} Starting on {datestart} Ending on {dateend}</tpl>',
							WeekFormat : "W",
							DatesFormat : "d/m/Y",
							Month : "M-Y",
							TwoMonthsTPL : '<tpl for=".">Period No.{numperiod} Starting on {datestart} Ending on {dateend}</tpl>',
							QuarterTPL : '<tpl for=".">Period No.{numperiod} Starting on {datestart} Ending on {dateend}</tpl>'
						},
						useStoreColor : false,
						descriptionWidth : 246,
						parentLists : false,
						launchEventOn : "click",
						editableEvents : true,
						ShowMenuItems : [ 1, 1, 1, 1, 1, 1, 1, 1 ],
						taskdd_ShowMenuItems : [ 1, 1, 1 ],
						moreMenuItems : [ i, h ],
						taskdd_BaseColor : "#6C90B4",
						taskdd_clsOver : "test_taskovercss_sched",
						taskdd_showqtip : true,
						taskdd_shownames : true
					},
					listbody : {
						periodType : e2cs.schedviews.subView.Month,
						headerUnit : e2cs.schedviews.Units.Days,
						headerUnitWidth : 25
					}
				},
				mview : {
					header : true,
					headerFormat : "Y-F",
					headerButtons : true,
					dayAction : "viewday",
					moreMenuItems : [ e, d ],
					showTaskcount : false,
					startDay : 0,
					taskStyle : "margin-top:2px;",
					showTaskList : true,
					showNumTasks : 10,
					TaskList_launchEventOn : "click",
					ShowMenuItems : [ 1, 1, 1, 1, 1, 1 ],
					TaskList_moreMenuItems : [ g, f ],
					TaskList_ShowMenuItems : [ 1, 1, 1 ]
				},
				wview : {
					headerlabel : "周 #",
					headerButtons : true,
					dayformatLabel : "D j",
					moreMenuItems : [ e, d ],
					style : "google",
					alldayTaksMore : "window",
					alldayTasksMaxView : 6,
					store : null,
					task_width : 25,
					tasksOffset : 40,
					headerDayClick : "viewday",
					ShowMenuItems : [ 1, 1, 1, 1, 1, 1 ],
					task_ShowMenuItems : [ 1, 1, 1, 1, 1 ],
					task_eventLaunch : "click",
					startDay : 0,
					task_clsOver : "test_taskovercss",
					forceTaskFit : true
				},
				dview : {
					header : true,
					headerFormat : "星期l - d - F  - Y",
					headerButtons : true,
					moreMenuItems : [],
					hourFormat : "h",
					startTime : "00:00:00 am",
					endTime : "11:59:59 pm",
					store : null,
					taskBaseColor : "#ffffff",
					task_width : 60,
					taskAdd_dblclick : true,
					taskAdd_timer_dblclick : true,
					useMultiColorTasks : false,
					multiColorTasks : [],
					tasks : [],
					moreMenuItems : [ e, d ],
					task_clsOver : "test_taskovercss",
					ShowMenuItems : [ 1, 1, 1, 1, 1, 1 ],
					task_DDeditable : true,
					task_eventLaunch : "click",
					task_ShowMenuItems : [ 1, 1, 1, 1, 1 ],
					customHTMLinpos : "before",
					forceTaskFit : true
				}
			});
	prueba.viewscheduler.on( {
		"headerClick" : {
			fn : function(l, j, m, k) {
			},
			scope : this
		},
		"beforePeriodChange" : {
			fn : function(j, k, l) {
				if (j == 1) {
				} else {
				}
				return true;
			},
			scope : this
		},
		"afterPeriodChange" : {
			fn : function(j, k) {
				if (j == 1) {
				} else {
				}
				updateStoreincalendar();
			}
		}
	});
	prueba.viewmonth.on( {
		"dayClick" : {
			fn : function(j, l, k) {
			},
			scope : this
		},
		"beforeMonthChange" : {
			fn : function(k, j) {
				prueba.currentdate = j;
				return true;
			},
			scope : this
		},
		"afterMonthChange" : {
			fn : function(j) {
				updateStoreincalendar();
			},
			scope : this
		}
	});
	prueba.viewweek.on( {
		"dblClickTaskAllDay" : {
			fn : function(j, k, l) {
				new CalendarPlanDetailView(j[1]);
			},
			scope : this
		},
		"beforeWeekChange" : {
			fn : function(k, j) {
				return true;
			}
		},
		"afterWeekChange" : {
			fn : function(j) {
				prueba.currentdate = j;
				updateStoreincalendar();
				return false;
			}
		}
	});
	prueba.viewday.on( {
		"beforeDayChange" : {
			fn : function(k, j) {
				return true;
			},
			scope : this
		},
		"afterDayChange" : {
			fn : function(j) {
				prueba.currentdate = j;
				updateStoreincalendar();
			},
			scope : this
		}
	});
	prueba.on( {
		"onReload" : {
			fn : function() {
				prueba.store.reload();
			}
		},
		"beforeContextMenuTask" : {
			fn : function(k, l, j, m) {
				return false;
			}
		},
		"beforeChangeDate" : {
			fn : function(k, j) {
				return true;
			}
		},
		"afterChangeDate" : {
			fn : function(k, j) {
				updateStoreincalendar();
			}
		},
		"onChangeView" : {
			fn : function(l, j, k) {
				updateStoreincalendar();
			},
			scope : this
		},
		"beforeChangeView" : {
			fn : function(l, j, k) {
				if (l == j) {
					return true;
				}
				return true;
			},
			scope : this
		},
		"taskAdd" : {
			fn : function(j) {
				new CalendarPlanForm(null, function() {
					updateStoreincalendar();
				});
			}
		},
		"taskDblClick" : {
			fn : function(j, k, l, m) {
				new CalendarPlanDetailView(j[1]);
			},
			scope : this
		},
		"beforeTaskDelete" : {
			fn : function(j, k) {
				return false;
			},
			scope : this
		},
		"onTaskDelete" : {
			fn : function(j) {
				var k = confirm("Delete event " + j[1] + " " + j[2]
						+ "...? YES/NO");
				return k;
			},
			scope : this
		},
		"afterTaskDelete" : {
			fn : function(j, k) {
				k ? alert("Event: " + j[1] + " " + j[2] + " Deleted")
						: alert("Event Delete was canceled..!");
				updateStoreincalendar();
			},
			scope : this
		},
		"beforeTaskEdit" : {
			fn : function(j, k) {
				return false;
			},
			scope : this
		},
		"onTaskEdit" : {
			fn : function(j) {
				return true;
			},
			scope : this
		},
		"afterTaskEdit" : {
			fn : function(j, k) {
				if (k) {
				} else {
					alert("Event Edit was canceled..!");
				}
				return false;
			},
			scope : this
		},
		"beforeTaskMove" : {
			fn : function(j, k, l, m) {
				return false;
			},
			scope : this
		},
		"TaskMoved" : {
			fn : function(k, j, l, m) {
				var n = 21;
				task = k;
				new CalendarPlanDetailView(task[0]);
			},
			scope : this
		},
		"customMenuAction" : {
			fn : function(m, k, j, o, l) {
				var n = "";
				if (k == "month") {
				} else {
					if (k == "day") {
						task = j;
						n = "Task id:" + task[0] + " " + task[1] + "<br>";
						n += "starts:" + task[2] + "<br>";
						n += "Ends:" + task[3] + "<br>";
						n += "contents:" + task[4] + "<br>";
						n += "index:" + task[5] + "<br>";
						n += "Test Menu:" + m + "<br>";
						Ext.Msg.alert("(Day) Task information" + k, n);
					} else {
						if (k == "week") {
						} else {
							if (k == "scheduler") {
								task = j;
								n = "Task id:" + task[0] + ":" + task[2]
										+ "<br>";
								n += "starts:" + task[3] + "<br>";
								n += "Ends:" + task[4] + "<br>";
								n += "contents:" + task[5] + "<br>";
								n += "index:" + task[6] + "<br>";
								n += "Test Menu:" + m + "<br>";
								Ext.Msg.alert("(Scheduler) Task information"
										+ k, n);
							}
						}
					}
				}
			},
			scope : this
		}
	});
	prueba.on( {
		"render" : {
			fn : function(j) {
				calendarStore.load();
				myMasktest = new Ext.LoadMask(j.id, {
					msg : e2cs.cal_locale.loadmaskText
				});
				calendarStore.on( {
					"beforeload" : {
						fn : function() {
							j.calendarMask.show();
						}
					},
					"load" : {
						fn : function(k, m, l) {
							if (k == false) {
								alert("加载日程任务数据出错！");
							} else {
								j.refreshCalendarView(true);
							}
							j.calendarMask.hide();
						}
					}
				});
				Ext.EventManager.onWindowResize(function() {
					j.refreshCalendarView();
				});
			}
		}
	});
	return prueba;
}
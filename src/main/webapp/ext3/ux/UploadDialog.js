Ext.namespace("Ext.ux.Utils");
Ext.ux.Utils.EventQueue = function(b, a) {
	if (!b) {
		throw "Handler is required.";
	}
	this.handler = b;
	this.scope = a || window;
	this.queue = [];
	this.is_processing = false;
	this.postEvent = function(c, d) {
		d = d || null;
		this.queue.push({
			event : c,
			data : d
		});
		if (!this.is_processing) {
			this.process();
		}
	};
	this.flushEventQueue = function() {
		this.queue = [];
	}, this.process = function() {
		while (this.queue.length > 0) {
			this.is_processing = true;
			var c = this.queue.shift();
			this.handler.call(this.scope, c.event, c.data);
		}
		this.is_processing = false;
	};
};
Ext.ux.Utils.FSA = function(c, b, a) {
	this.current_state = c;
	this.trans_table = b || {};
	this.trans_table_scope = a || window;
	Ext.ux.Utils.FSA.superclass.constructor.call(this, this.processEvent, this);
};
Ext
		.extend(
				Ext.ux.Utils.FSA,
				Ext.ux.Utils.EventQueue,
				{
					current_state : null,
					trans_table : null,
					trans_table_scope : null,
					state : function() {
						return this.current_state;
					},
					processEvent : function(a, d) {
						var h = this.currentStateEventTransitions(a);
						if (!h) {
							throw "State '" + this.current_state
									+ "' has no transition for event '" + a
									+ "'.";
						}
						for ( var e = 0, f = h.length; e < f; e++) {
							var g = h[e];
							var j = g.predicate || g.p || true;
							var c = g.action || g.a || Ext.emptyFn;
							var b = g.state || g.s || this.current_state;
							var k = g.scope || this.trans_table_scope;
							if (this.computePredicate(j, k, d, a)) {
								this.callAction(c, k, d, a);
								this.current_state = b;
								return;
							}
						}
						throw "State '" + this.current_state
								+ "' has no transition for event '" + a
								+ "' in current context";
					},
					currentStateEventTransitions : function(a) {
						return this.trans_table[this.current_state] ? this.trans_table[this.current_state][a] || false
								: false;
					},
					computePredicate : function(c, e, g, f) {
						var b = false;
						switch (Ext.type(c)) {
						case "function":
							b = c.call(e, g, f, this);
							break;
						case "array":
							b = true;
							for ( var d = 0, a = c.length; b && (d < a); d++) {
								if (Ext.type(c[d]) == "function") {
									b = c[d].call(e, g, f, this);
								} else {
									throw [ "Predicate: ", c[d],
											' is not callable in "',
											this.current_state,
											'" state for event "', f ].join("");
								}
							}
							break;
						case "boolean":
							b = c;
							break;
						default:
							throw [ "Predicate: ", c, ' is not callable in "',
									this.current_state, '" state for event "',
									f ].join("");
						}
						return b;
					},
					callAction : function(f, c, e, d) {
						switch (Ext.type(f)) {
						case "array":
							for ( var b = 0, a = f.length; b < a; b++) {
								if (Ext.type(f[b]) == "function") {
									f[b].call(c, e, d, this);
								} else {
									throw [ "Action: ", f[b],
											' is not callable in "',
											this.current_state,
											'" state for event "', d ].join("");
								}
							}
							break;
						case "function":
							f.call(c, e, d, this);
							break;
						default:
							throw [ "Action: ", f, ' is not callable in "',
									this.current_state, '" state for event "',
									d ].join("");
						}
					}
				});
Ext.namespace("Ext.ux.UploadDialog");
Ext.ux.UploadDialog.BrowseButton = Ext
		.extend(
				Ext.Button,
				{
					input_name : "file",
					input_file : null,
					original_handler : null,
					original_scope : null,
					initComponent : function() {
						Ext.ux.UploadDialog.BrowseButton.superclass.initComponent
								.call(this);
						this.original_handler = this.handler || null;
						this.original_scope = this.scope || window;
						this.handler = null;
						this.scope = null;
					},
					onRender : function(b, a) {
						Ext.ux.UploadDialog.BrowseButton.superclass.onRender
								.call(this, b, a);
						this.createInputFile();
					},
					createInputFile : function() {
						var c = this.el;
						c.position("relative");
						this.input_file = Ext.DomHelper
								.insertAfter(
										c,
										{
											tag : "input",
											type : "file",
											size : 1,
											name : this.input_name
													|| Ext.id(this.el),
											style : "position: absolute; display: block; border: none; cursor: pointer"
										}, true);
						var b = c.getBox();
						this.input_file.setStyle("font-size", (b.width * 0.5)
								+ "px");
						var d = this.input_file.getBox();
						var a = {
							x : 3,
							y : 3
						};
						if (Ext.isIE) {
							a = {
								x : 0,
								y : 3
							};
						}
						this.input_file.setLeft(b.width - d.width + a.x + "px");
						this.input_file
								.setTop(b.height - d.height + a.y + "px");
						this.input_file.setOpacity(0);
						if (this.handleMouseEvents) {
							this.input_file.on("mouseover", this.onMouseOver,
									this);
							this.input_file.on("mousedown", this.onMouseDown,
									this);
						}
						if (this.tooltip) {
							if (typeof this.tooltip == "object") {
								Ext.QuickTips.register(Ext.apply({
									target : this.input_file
								}, this.tooltip));
							} else {
								this.input_file.dom[this.tooltipType] = this.tooltip;
							}
						}
						this.input_file.on("change", this.onInputFileChange,
								this);
						this.input_file.on("click", function(f) {
							f.stopPropagation();
						});
					},
					detachInputFile : function(b) {
						var a = this.input_file;
						b = b || false;
						if (typeof this.tooltip == "object") {
							Ext.QuickTips.unregister(this.input_file);
						} else {
							this.input_file.dom[this.tooltipType] = null;
						}
						this.input_file.removeAllListeners();
						this.input_file = null;
						if (!b) {
							this.createInputFile();
						}
						return a;
					},
					getInputFile : function() {
						return this.input_file;
					},
					disable : function() {
						Ext.ux.UploadDialog.BrowseButton.superclass.disable
								.call(this);
						this.input_file.dom.disabled = true;
					},
					enable : function() {
						Ext.ux.UploadDialog.BrowseButton.superclass.enable
								.call(this);
						this.input_file.dom.disabled = false;
					},
					destroy : function() {
						var a = this.detachInputFile(true);
						a.remove();
						a = null;
						Ext.ux.UploadDialog.BrowseButton.superclass.destroy
								.call(this);
					},
					onInputFileChange : function() {
						if (this.original_handler) {
							this.original_handler.call(this.original_scope,
									this);
						}
					}
				});
Ext.ux.UploadDialog.TBBrowseButton = Ext.extend(
		Ext.ux.UploadDialog.BrowseButton, {
			hideParent : true,
			onDestroy : function() {
				Ext.ux.UploadDialog.TBBrowseButton.superclass.onDestroy
						.call(this);
				if (this.container) {
					this.container.remove();
				}
			}
		});
Ext.ux.UploadDialog.FileRecord = Ext.data.Record.create([ {
	name : "fileId",
	type : "int"
}, {
	name : "filename"
}, {
	name : "filepath"
}, {
	name : "state",
	type : "int"
}, {
	name : "note"
}, {
	name : "input_element"
} ]);
Ext.ux.UploadDialog.FileRecord.STATE_QUEUE = 0;
Ext.ux.UploadDialog.FileRecord.STATE_FINISHED = 1;
Ext.ux.UploadDialog.FileRecord.STATE_FAILED = 2;
Ext.ux.UploadDialog.FileRecord.STATE_PROCESSING = 3;
Ext.ux.UploadDialog.Dialog = function(a) {
	var b = {
		border : false,
		width : 450,
		height : 300,
		minWidth : 450,
		minHeight : 300,
		plain : true,
		constrainHeader : true,
		draggable : true,
		closable : true,
		maximizable : false,
		minimizable : false,
		resizable : true,
		autoDestroy : true,
		closeAction : "close",
		title : this.i18n.title,
		cls : "ext-ux-uploaddialog-dialog",
		url : "",
		base_params : {},
		permitted_extensions : [],
		reset_on_hide : true,
		allow_close_on_upload : false,
		upload_autostart : false
	};
	a = Ext.applyIf(a || {}, b);
	a.layout = "absolute";
	Ext.ux.UploadDialog.Dialog.superclass.constructor.call(this, a);
};
Ext
		.extend(
				Ext.ux.UploadDialog.Dialog,
				Ext.Window,
				{
					fsa : null,
					state_tpl : null,
					form : null,
					grid_panel : null,
					progress_bar : null,
					is_uploading : false,
					initial_queued_count : 0,
					upload_frame : null,
					iconCls : "menu-file",
					file_cat : "others",
					callback : null,
					initComponent : function() {
						Ext.ux.UploadDialog.Dialog.superclass.initComponent
								.call(this);
						var a = {
							"created" : {
								"window-render" : [ {
									action : [ this.createForm,
											this.createProgressBar,
											this.createGrid ],
									state : "rendering"
								} ],
								"destroy" : [ {
									action : this.flushEventQueue,
									state : "destroyed"
								} ]
							},
							"rendering" : {
								"grid-render" : [ {
									action : [ this.fillToolbar,
											this.updateToolbar ],
									state : "ready"
								} ],
								"destroy" : [ {
									action : this.flushEventQueue,
									state : "destroyed"
								} ]
							},
							"ready" : {
								"file-selected" : [
										{
											predicate : [
													this.fireFileTestEvent,
													this.isPermittedFile ],
											action : this.addFileToUploadQueue,
											state : "adding-file"
										}, {} ],
								"grid-selection-change" : [ {
									action : this.updateToolbar
								} ],
								"remove-files" : [ {
									action : [ this.removeFiles,
											this.fireFileRemoveEvent ]
								} ],
								"reset-queue" : [ {
									action : [ this.resetQueue,
											this.fireResetQueueEvent ]
								} ],
								"start-upload" : [
										{
											predicate : this.hasUnuploadedFiles,
											action : [
													this.setUploadingFlag,
													this.saveInitialQueuedCount,
													this.updateToolbar,
													this.updateProgressBar,
													this.prepareNextUploadTask,
													this.fireUploadStartEvent ],
											state : "uploading"
										}, {} ],
								"stop-upload" : [ {} ],
								"hide" : [
										{
											predicate : [ this.isNotEmptyQueue,
													this.getResetOnHide ],
											action : [ this.resetQueue,
													this.fireResetQueueEvent ]
										}, {} ],
								"destroy" : [ {
									action : this.flushEventQueue,
									state : "destroyed"
								} ]
							},
							"adding-file" : {
								"file-added" : [
										{
											predicate : this.isUploading,
											action : [
													this.incInitialQueuedCount,
													this.updateProgressBar,
													this.fireFileAddEvent ],
											state : "uploading"
										},
										{
											predicate : this.getUploadAutostart,
											action : [ this.startUpload,
													this.fireFileAddEvent ],
											state : "ready"
										},
										{
											action : [ this.updateToolbar,
													this.fireFileAddEvent ],
											state : "ready"
										} ]
							},
							"uploading" : {
								"file-selected" : [
										{
											predicate : [
													this.fireFileTestEvent,
													this.isPermittedFile ],
											action : this.addFileToUploadQueue,
											state : "adding-file"
										}, {} ],
								"grid-selection-change" : [ {} ],
								"start-upload" : [ {} ],
								"stop-upload" : [
										{
											predicate : this.hasUnuploadedFiles,
											action : [ this.resetUploadingFlag,
													this.abortUpload,
													this.updateToolbar,
													this.updateProgressBar,
													this.fireUploadStopEvent ],
											state : "ready"
										},
										{
											action : [
													this.resetUploadingFlag,
													this.abortUpload,
													this.updateToolbar,
													this.updateProgressBar,
													this.fireUploadStopEvent,
													this.fireUploadCompleteEvent ],
											state : "ready"
										} ],
								"file-upload-start" : [ {
									action : [ this.uploadFile,
											this.findUploadFrame,
											this.fireFileUploadStartEvent ]
								} ],
								"file-upload-success" : [
										{
											predicate : this.hasUnuploadedFiles,
											action : [ this.resetUploadFrame,
													this.updateRecordState,
													this.updateProgressBar,
													this.prepareNextUploadTask,
													this.fireUploadSuccessEvent ]
										},
										{
											action : [
													this.resetUploadFrame,
													this.resetUploadingFlag,
													this.updateRecordState,
													this.updateToolbar,
													this.updateProgressBar,
													this.fireUploadSuccessEvent,
													this.fireUploadCompleteEvent ],
											state : "ready"
										} ],
								"file-upload-error" : [
										{
											predicate : this.hasUnuploadedFiles,
											action : [ this.resetUploadFrame,
													this.updateRecordState,
													this.updateProgressBar,
													this.prepareNextUploadTask,
													this.fireUploadErrorEvent ]
										},
										{
											action : [
													this.resetUploadFrame,
													this.resetUploadingFlag,
													this.updateRecordState,
													this.updateToolbar,
													this.updateProgressBar,
													this.fireUploadErrorEvent,
													this.fireUploadCompleteEvent ],
											state : "ready"
										} ],
								"file-upload-failed" : [
										{
											predicate : this.hasUnuploadedFiles,
											action : [ this.resetUploadFrame,
													this.updateRecordState,
													this.updateProgressBar,
													this.prepareNextUploadTask,
													this.fireUploadFailedEvent ]
										},
										{
											action : [
													this.resetUploadFrame,
													this.resetUploadingFlag,
													this.updateRecordState,
													this.updateToolbar,
													this.updateProgressBar,
													this.fireUploadFailedEvent,
													this.fireUploadCompleteEvent ],
											state : "ready"
										} ],
								"hide" : [
										{
											predicate : this.getResetOnHide,
											action : [ this.stopUpload,
													this.repostHide ]
										}, {} ],
								"destroy" : [
										{
											predicate : this.hasUnuploadedFiles,
											action : [ this.resetUploadingFlag,
													this.abortUpload,
													this.fireUploadStopEvent,
													this.flushEventQueue ],
											state : "destroyed"
										},
										{
											action : [
													this.resetUploadingFlag,
													this.abortUpload,
													this.fireUploadStopEvent,
													this.fireUploadCompleteEvent,
													this.flushEventQueue ],
											state : "destroyed"
										} ]
							},
							"destroyed" : {}
						};
						this.fsa = new Ext.ux.Utils.FSA("created", a, this);
						this.addEvents({
							"filetest" : true,
							"fileadd" : true,
							"fileremove" : true,
							"resetqueue" : true,
							"uploadsuccess" : true,
							"uploaderror" : true,
							"uploadfailed" : true,
							"uploadstart" : true,
							"uploadstop" : true,
							"uploadcomplete" : true,
							"fileuploadstart" : true
						});
						this.on("render", this.onWindowRender, this);
						this.on("beforehide", this.onWindowBeforeHide, this);
						this.on("hide", this.onWindowHide, this);
						this.on("destroy", this.onWindowDestroy, this);
						this.state_tpl = new Ext.Template(
								"<div class='ext-ux-uploaddialog-state ext-ux-uploaddialog-state-{state}'>&#160;</div>")
								.compile();
					},
					createForm : function() {
						this.form = Ext.DomHelper
								.append(
										this.body,
										{
											tag : "form",
											method : "post",
											action : this.url,
											style : "position: absolute; left: -100px; top: -100px; width: 100px; height: 100px"
										});
					},
					createProgressBar : function() {
						this.progress_bar = this.add(new Ext.ProgressBar({
							x : 0,
							y : 0,
							anchor : "0",
							value : 0,
							text : this.i18n.progress_waiting_text
						}));
					},
					createGrid : function() {
						var b = new Ext.data.Store({
							proxy : new Ext.data.MemoryProxy([]),
							reader : new Ext.data.JsonReader({},
									Ext.ux.UploadDialog.FileRecord),
							sortInfo : {
								field : "state",
								direction : "DESC"
							},
							pruneModifiedRecords : true
						});
						var a = new Ext.grid.ColumnModel([
								{
									header : this.i18n.state_col_title,
									width : this.i18n.state_col_width,
									resizable : false,
									dataIndex : "state",
									sortable : true,
									renderer : this.renderStateCell
											.createDelegate(this)
								},
								{
									header : this.i18n.filename_col_title,
									width : this.i18n.filename_col_width,
									dataIndex : "filename",
									sortable : true,
									renderer : this.renderFilenameCell
											.createDelegate(this)
								},
								{
									header : this.i18n.note_col_title,
									width : this.i18n.note_col_width,
									dataIndex : "note",
									sortable : true,
									renderer : this.renderNoteCell
											.createDelegate(this)
								} ]);
						this.grid_panel = new Ext.grid.GridPanel({
							ds : b,
							cm : a,
							x : 0,
							y : 22,
							anchor : "0 -22",
							border : true,
							viewConfig : {
								autoFill : true,
								forceFit : true
							},
							bbar : new Ext.Toolbar()
						});
						this.grid_panel.on("render", this.onGridRender, this);
						this.add(this.grid_panel);
						this.grid_panel.getSelectionModel().on(
								"selectionchange", this.onGridSelectionChange,
								this);
					},
					fillToolbar : function() {
						var a = this.grid_panel.getBottomToolbar();
						a.x_buttons = {};
						a.x_buttons.add = a
								.addItem(new Ext.ux.UploadDialog.TBBrowseButton(
										{
											text : this.i18n.add_btn_text,
											tooltip : this.i18n.add_btn_tip,
											iconCls : "ext-ux-uploaddialog-addbtn",
											handler : this.onAddButtonFileSelected,
											scope : this
										}));
						a.x_buttons.remove = a.addButton({
							text : this.i18n.remove_btn_text,
							tooltip : this.i18n.remove_btn_tip,
							iconCls : "ext-ux-uploaddialog-removebtn",
							handler : this.onRemoveButtonClick,
							scope : this
						});
						a.x_buttons.reset = a.addButton({
							text : this.i18n.reset_btn_text,
							tooltip : this.i18n.reset_btn_tip,
							iconCls : "ext-ux-uploaddialog-resetbtn",
							handler : this.onResetButtonClick,
							scope : this
						});
						a.add("-");
						a.x_buttons.upload = a.addButton({
							text : this.i18n.upload_btn_start_text,
							tooltip : this.i18n.upload_btn_start_tip,
							iconCls : "ext-ux-uploaddialog-uploadstartbtn",
							handler : this.onUploadButtonClick,
							scope : this
						});
						a.add("-");
						a.x_buttons.indicator = a
								.addItem(new Ext.Toolbar.Item(
										Ext.DomHelper
												.append(
														a.getEl(),
														{
															id : "x-button-indicator",
															tag : "div",
															cls : "ext-ux-uploaddialog-indicator-stoped",
															html : "&#160"
														})));
						a.add("->");
						a.x_buttons.close = a.addButton({
							iconCls : "btn-ok",
							text : this.i18n.close_btn_text,
							tooltip : this.i18n.close_btn_tip,
							handler : this.onCloseButtonClick,
							scope : this
						});
					},
					renderStateCell : function(f, a, b, d, e, c) {
						return this.state_tpl.apply({
							state : f
						});
					},
					renderFilenameCell : function(i, a, c, e, h, d) {
						var b = this.grid_panel.getView();
						var g = function() {
							try {
								Ext.fly(b.getCell(e, h)).child(
										".x-grid3-cell-inner").dom["qtip"] = i;
							} catch (f) {
							}
						};
						g.defer(1000);
						return i;
					},
					renderNoteCell : function(i, a, c, e, h, d) {
						var b = this.grid_panel.getView();
						var g = function() {
							try {
								Ext.fly(b.getCell(e, h)).child(
										".x-grid3-cell-inner").dom["qtip"] = i;
							} catch (f) {
							}
						};
						g.defer(1000);
						return i;
					},
					getFileExtension : function(b) {
						var a = null;
						var c = b.split(".");
						if (c.length > 1) {
							a = c.pop();
						}
						return a;
					},
					isPermittedFileType : function(b) {
						var a = true;
						if (this.permitted_extensions.length > 0) {
							a = this.permitted_extensions.indexOf(this
									.getFileExtension(b)) != -1;
						}
						return a;
					},
					isPermittedFile : function(c) {
						var a = false;
						var b = c.getInputFile().dom.value;
						if (this.isPermittedFileType(b)) {
							a = true;
						} else {
							Ext.Msg
									.alert(
											this.i18n.error_msgbox_title,
											String
													.format(
															this.i18n.err_file_type_not_permitted,
															b,
															this.permitted_extensions
																	.join(this.i18n.permitted_extensions_join_str)));
							a = false;
						}
						return a;
					},
					fireFileTestEvent : function(a) {
						return this.fireEvent("filetest", this, a
								.getInputFile().dom.value) !== false;
					},
					addFileToUploadQueue : function(c) {
						var a = c.detachInputFile();
						a.appendTo(this.form);
						a.setStyle("width", "100px");
						a.dom.disabled = true;
						var b = this.grid_panel.getStore();
						b.add(new Ext.ux.UploadDialog.FileRecord({
							state : Ext.ux.UploadDialog.FileRecord.STATE_QUEUE,
							filename : a.dom.value,
							note : this.i18n.note_queued_to_upload,
							input_element : a
						}));
						this.fsa.postEvent("file-added", a.dom.value);
					},
					fireFileAddEvent : function(a) {
						this.fireEvent("fileadd", this, a);
					},
					updateProgressBar : function() {
						if (this.is_uploading) {
							var b = this.getQueuedCount(true);
							var a = 1 - b / this.initial_queued_count;
							this.progress_bar.updateProgress(a, String.format(
									this.i18n.progress_uploading_text,
									this.initial_queued_count - b,
									this.initial_queued_count));
						} else {
							this.progress_bar.updateProgress(0,
									this.i18n.progress_waiting_text);
						}
					},
					updateToolbar : function() {
						var a = this.grid_panel.getBottomToolbar();
						if (this.is_uploading) {
							a.x_buttons.remove.disable();
							a.x_buttons.reset.disable();
							a.x_buttons.upload.enable();
							if (!this.getAllowCloseOnUpload()) {
								a.x_buttons.close.disable();
							}
							Ext
									.fly(a.x_buttons.indicator.getEl())
									.replaceClass(
											"ext-ux-uploaddialog-indicator-stoped",
											"ext-ux-uploaddialog-indicator-processing");
							a.x_buttons.upload
									.setIconClass("ext-ux-uploaddialog-uploadstopbtn");
							a.x_buttons.upload
									.setText(this.i18n.upload_btn_stop_text);
							a.x_buttons.upload.getEl().child(
									a.x_buttons.upload.buttonSelector).dom[a.x_buttons.upload.tooltipType] = this.i18n.upload_btn_stop_tip;
						} else {
							a.x_buttons.remove.enable();
							a.x_buttons.reset.enable();
							a.x_buttons.close.enable();
							Ext
									.fly(a.x_buttons.indicator.getEl())
									.replaceClass(
											"ext-ux-uploaddialog-indicator-processing",
											"ext-ux-uploaddialog-indicator-stoped");
							a.x_buttons.upload
									.setIconClass("ext-ux-uploaddialog-uploadstartbtn");
							a.x_buttons.upload
									.setText(this.i18n.upload_btn_start_text);
							if (this.getQueuedCount() > 0) {
								a.x_buttons.upload.enable();
							} else {
								a.x_buttons.upload.disable();
							}
							if (this.grid_panel.getSelectionModel()
									.hasSelection()) {
								a.x_buttons.remove.enable();
							} else {
								a.x_buttons.remove.disable();
							}
							if (this.grid_panel.getStore().getCount() > 0) {
								a.x_buttons.reset.enable();
							} else {
								a.x_buttons.reset.disable();
							}
						}
					},
					saveInitialQueuedCount : function() {
						this.initial_queued_count = this.getQueuedCount();
					},
					incInitialQueuedCount : function() {
						this.initial_queued_count++;
					},
					setUploadingFlag : function() {
						this.is_uploading = true;
					},
					resetUploadingFlag : function() {
						this.is_uploading = false;
					},
					prepareNextUploadTask : function() {
						var b = this.grid_panel.getStore();
						var a = null;
						b
								.each(function(c) {
									if (!a
											&& c.get("state") == Ext.ux.UploadDialog.FileRecord.STATE_QUEUE) {
										a = c;
									} else {
										c.get("input_element").dom.disabled = true;
									}
								});
						a.get("input_element").dom.disabled = false;
						a
								.set(
										"state",
										Ext.ux.UploadDialog.FileRecord.STATE_PROCESSING);
						a.set("note", this.i18n.note_processing);
						a.commit();
						this.fsa.postEvent("file-upload-start", a);
					},
					fireUploadStartEvent : function() {
						this.fireEvent("uploadstart", this);
					},
					removeFiles : function(e) {
						var b = this.grid_panel.getStore();
						for ( var c = 0, a = e.length; c < a; c++) {
							var d = e[c];
							d.get("input_element").remove();
							b.remove(d);
						}
					},
					fireFileRemoveEvent : function(c) {
						for ( var b = 0, a = c.length; b < a; b++) {
							this.fireEvent("fileremove", this, c[b]
									.get("filename"));
						}
					},
					resetQueue : function() {
						var a = this.grid_panel.getStore();
						a.each(function(b) {
							b.get("input_element").remove();
						});
						a.removeAll();
					},
					fireResetQueueEvent : function() {
						this.fireEvent("resetqueue", this);
					},
					uploadFile : function(a) {
						var b = this.base_params || this.baseParams
								|| this.params;
						Ext.apply(b, {
							file_cat : this.file_cat
						});
						Ext.Ajax.request({
							url : this.url,
							params : b,
							method : "POST",
							form : this.form,
							isUpload : true,
							success : this.onAjaxSuccess,
							failure : this.onAjaxFailure,
							scope : this,
							record : a
						});
					},
					fireFileUploadStartEvent : function(a) {
						this.fireEvent("fileuploadstart", this, a
								.get("filename"));
					},
					updateRecordState : function(a) {
						if ("success" in a.response && a.response.success) {
							a.record
									.set(
											"state",
											Ext.ux.UploadDialog.FileRecord.STATE_FINISHED);
							a.record.set("note", a.response.message
									|| a.response.error
									|| this.i18n.note_upload_success);
						} else {
							a.record
									.set(
											"state",
											Ext.ux.UploadDialog.FileRecord.STATE_FAILED);
							a.record.set("note", a.response.message
									|| a.response.error
									|| this.i18n.note_upload_error);
						}
						a.record.commit();
					},
					fireUploadSuccessEvent : function(a) {
						this.fireEvent("uploadsuccess", this, a.record
								.get("filename"), a.response);
					},
					fireUploadErrorEvent : function(a) {
						this.fireEvent("uploaderror", this, a.record
								.get("filename"), a.response);
					},
					fireUploadFailedEvent : function(a) {
						this.fireEvent("uploadfailed", this, a.record
								.get("filename"));
					},
					fireUploadCompleteEvent : function() {
						this.fireEvent("uploadcomplete", this);
					},
					findUploadFrame : function() {
						this.upload_frame = Ext.getBody().child(
								"iframe.x-hidden:last");
					},
					resetUploadFrame : function() {
						this.upload_frame = null;
					},
					removeUploadFrame : function() {
						if (this.upload_frame) {
							this.upload_frame.removeAllListeners();
							this.upload_frame.dom.src = "about:blank";
							this.upload_frame.remove();
						}
						this.upload_frame = null;
					},
					abortUpload : function() {
						this.removeUploadFrame();
						var b = this.grid_panel.getStore();
						var a = null;
						b
								.each(function(c) {
									if (c.get("state") == Ext.ux.UploadDialog.FileRecord.STATE_PROCESSING) {
										a = c;
										return false;
									}
								});
						a.set("state",
								Ext.ux.UploadDialog.FileRecord.STATE_FAILED);
						a.set("note", this.i18n.note_aborted);
						a.commit();
					},
					fireUploadStopEvent : function() {
						this.fireEvent("uploadstop", this);
					},
					repostHide : function() {
						this.fsa.postEvent("hide");
					},
					flushEventQueue : function() {
						this.fsa.flushEventQueue();
					},
					onWindowRender : function() {
						this.fsa.postEvent("window-render");
					},
					onWindowBeforeHide : function() {
						return this.isUploading() ? this
								.getAllowCloseOnUpload() : true;
					},
					onWindowHide : function() {
						this.fsa.postEvent("hide");
					},
					onWindowDestroy : function() {
						this.fsa.postEvent("destroy");
					},
					onGridRender : function() {
						this.fsa.postEvent("grid-render");
					},
					onGridSelectionChange : function() {
						this.fsa.postEvent("grid-selection-change");
					},
					onAddButtonFileSelected : function(a) {
						this.fsa.postEvent("file-selected", a);
					},
					onUploadButtonClick : function() {
						if (this.is_uploading) {
							this.fsa.postEvent("stop-upload");
						} else {
							this.fsa.postEvent("start-upload");
						}
					},
					onRemoveButtonClick : function() {
						var a = this.grid_panel.getSelectionModel()
								.getSelections();
						this.fsa.postEvent("remove-files", a);
					},
					onResetButtonClick : function() {
						this.fsa.postEvent("reset-queue");
					},
					onCloseButtonClick : function() {
						var b = this.grid_panel.getStore();
						var d = new Array();
						for ( var c = 0; c < b.getCount(); c++) {
							var a = b.getAt(c);
							if (a.data.state == Ext.ux.UploadDialog.FileRecord.STATE_FINISHED) {
								d.push(a.data);
							}
						}
						if (this.callback != null) {
							this.callback.call(this, d);
						}
						this.close();
					},
					onAjaxSuccess : function(c, d) {
						var a = {
							"success" : false,
							"error" : this.i18n.note_upload_error
						};
						try {
							var b = c.responseText;
							var f = b.match(/^<[^>]+>((?:.|\n)*)<\/[^>]+>$/);
							if (f) {
								b = f[1];
							}
							var a = Ext.util.JSON.decode(b);
							d.record.data.filename = a.fileName;
							d.record.data.fileId = a.fileId;
							d.record.data.filepath = a.filePath;
						} catch (h) {
							console.info(h);
						}
						var g = {
							record : d.record,
							response : a
						};
						if (a.success == true) {
							this.fsa.postEvent("file-upload-success", g);
						} else {
							this.fsa.postEvent("file-upload-error", g);
						}
					},
					onAjaxFailure : function(a, b) {
						var c = {
							record : b.record,
							response : {
								"success" : false,
								"error" : this.i18n.note_upload_failed
							}
						};
						this.fsa.postEvent("file-upload-failed", c);
					},
					startUpload : function() {
						this.fsa.postEvent("start-upload");
					},
					stopUpload : function() {
						this.fsa.postEvent("stop-upload");
					},
					getUrl : function() {
						return this.url;
					},
					setUrl : function(a) {
						this.url = a;
					},
					getBaseParams : function() {
						return this.base_params;
					},
					setBaseParams : function(a) {
						this.base_params = a;
					},
					getUploadAutostart : function() {
						return this.upload_autostart;
					},
					setUploadAutostart : function(a) {
						this.upload_autostart = a;
					},
					getAllowCloseOnUpload : function() {
						return this.allow_close_on_upload;
					},
					setAllowCloseOnUpload : function(a) {
						this.allow_close_on_upload;
					},
					getResetOnHide : function() {
						return this.reset_on_hide;
					},
					setResetOnHide : function(a) {
						this.reset_on_hide = a;
					},
					getPermittedExtensions : function() {
						return this.permitted_extensions;
					},
					setPermittedExtensions : function(a) {
						this.permitted_extensions = a;
					},
					isUploading : function() {
						return this.is_uploading;
					},
					isNotEmptyQueue : function() {
						return this.grid_panel.getStore().getCount() > 0;
					},
					getQueuedCount : function(b) {
						var c = 0;
						var a = this.grid_panel.getStore();
						a
								.each(function(d) {
									if (d.get("state") == Ext.ux.UploadDialog.FileRecord.STATE_QUEUE) {
										c++;
									}
									if (b
											&& d.get("state") == Ext.ux.UploadDialog.FileRecord.STATE_PROCESSING) {
										c++;
									}
								});
						return c;
					},
					hasUnuploadedFiles : function() {
						return this.getQueuedCount() > 0;
					}
				});
var p = Ext.ux.UploadDialog.Dialog.prototype;
p.i18n = {
	title : "上传文件",
	state_col_title : "状态",
	state_col_width : 70,
	filename_col_title : "文件名",
	filename_col_width : 230,
	note_col_title : "备注",
	note_col_width : 150,
	add_btn_text : "添加",
	add_btn_tip : "添加文件到上传队列。",
	remove_btn_text : "删除",
	remove_btn_tip : "从上传队列删除文件。",
	reset_btn_text : "重置",
	reset_btn_tip : "重置队列。",
	upload_btn_start_text : "开始上传",
	upload_btn_stop_text : "中断上传",
	upload_btn_start_tip : "上传文件对列。",
	upload_btn_stop_tip : "停止上传。",
	close_btn_text : "确定",
	close_btn_tip : "关闭上传对话框。",
	progress_waiting_text : "等待...",
	progress_uploading_text : "上传中: {0} 的 {1} 文件集合成功。",
	error_msgbox_title : "错误",
	permitted_extensions_join_str : ",",
	err_file_type_not_permitted : "不支持上传该类型文件.<br/>请选择下列类型的文件集合 {1}",
	note_queued_to_upload : "上传的队列。",
	note_processing : "上传中...",
	note_upload_failed : "当前请求过多，服务器正忙，不能及时响应或者因特网服务器发生错误。",
	note_upload_success : "成功。",
	note_upload_error : "上传文件出错。",
	note_aborted : "已经被用户中断"
};
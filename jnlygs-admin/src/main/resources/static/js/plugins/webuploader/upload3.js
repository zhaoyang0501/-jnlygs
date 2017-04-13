$(function() {
	var $list = $(".imglist"), // 图片容器
	uploader, // 实例化
	ratio = window.devicePixelRatio || 1, // 优化retina, 在retina下这个值是2
	thumbnailWidth = 110 * ratio, // 缩略图大小
	thumbnailHeight = 110 * ratio, // 所有文件的进度信息，key为file id
	uploader = WebUploader.create({
		auto : false, // 是否自动上传
		pick : {
			id : '#imgPicker',
			innerHTML : '点击选择图片',
			multiple : false
		},
		swf : '${pageContext.request.contextPath}/js/Uploader.swf', // 在这里必需要引入swf文件，webuploader初始化要用
		server : $.common.getContextPath() + "/book/upload",
		duplicate : true,// 是否可重复选择同一文件
		resize : false,
		compress : null,// 图片不压缩
		chunked : true, // 分片处理
		chunkSize : 5 * 1024 * 1024, // 每片5M
		chunkRetry : false,// 如果失败，则不重试
		threads : 1,// 上传并发数。允许同时最大上传进程数。
		// 禁掉全局的拖拽功能。这样不会出现图片拖进页面的时候，把图片打开。
		disableGlobalDnd : true
	});

	uploader.onUploadProgress = function(file, percentage) {
		console.log(percentage);
		var $li = $('#' + file.id), $percent = $li.find('.progress span');
		$percent.css('width', percentage * 100 + '%');
	};
	
	// 当有文件添加进来的时候
	uploader.on("fileQueued", function(file) {
		$list.empty();
		//$("#imgPicker").text('开始上传');
		var $li = $('<li id="' + file.id + '">' + '<p class="imgWrap"></p>'
				+ '<p class="progress"><span class="text">0%</span> <span class="percentage"></span></p>' + '</li>');
		var $btns = $('<div class="file-panel">' + '<span class="cancel">删除</span>').appendTo($li);
		var $prgress = $li.find('p.progress span');
		var $wrap = $li.find('p.imgWrap');
		$wrap.text('预览中');
		uploader.makeThumb(file, function(error, src) {
			if (error) {
				$wrap.text('不能预览');
				return;
			}
			var img = $('<img src="' + src + '">');
			$wrap.empty().append(img);
		}, thumbnailWidth, thumbnailHeight);
		file.rotation = 0;
		$li.on('mouseenter', function() {
			$btns.stop().animate({
				height : 30
			});
		});

		$li.on('mouseleave', function() {
			$btns.stop().animate({
				height : 0
			});
		});
		$btns.on('click', 'span', function() {
			var index = $(this).index(), deg;
			console.log(index);
			switch (index) {
			case 0:
				uploader.removeFile(file);
				return;
			case 1:
				file.rotation += 90;
				break;
			case 2:
				file.rotation -= 90;
				break;
			}
		});
		$li.appendTo($list);
	});

	// 当文件上传成功时触发。
	uploader.on("uploadSuccess", function(file, response) {
		$("#img").val(response.datas);
		$("#" + file.id).find("p.state").text("已上传");
	});

	uploader.on("uploadError", function(file) {
		$("#" + file.id).find("p.state").text("上传出错");
		uploader.cancelFile(file);
		uploader.removeFile(file, true);
		uploader.reset();
	});

	$("#imguploadBtn").on("click", function() {
		uploader.upload();
	})

});

$(function() {
	var $list = $("#filelist");
	var fileSize = 0; // 总文件大小
	var fileName = []; // 文件名列表
	var fileSizeOneByOne = [];// 每个文件大小
	var uploader;// 实例化
	uploader = WebUploader.create({
		auto : false, // 是否自动上传
		pick : {
			id : '#filePicker',
			innerHTML : '点击选择文件',
			name : "multiFile"
		},
		swf : '${pageContext.request.contextPath}/js/Uploader.swf',
		server : $.common.getContextPath() + "/book/upload",
		duplicate : true, // 同一文件是否可重复选择
		resize : false,
		compress : null,// 图片不压缩
		chunked : true, // 分片
		chunkSize : 5 * 1024 * 1024, // 每片5M
		chunkRetry : false,// 如果失败，则不重试
		threads : 1,// 上传并发数。允许同时最大上传进程数。
		// 禁掉全局的拖拽功能。这样不会出现图片拖进页面的时候，把图片打开。
		disableGlobalDnd : true
	});

	// 当有文件添加进来的时候
	uploader.on("fileQueued", function(file) {
		$list.empty();
		$list.append("<div id='" + file.id + "' class='item'>" + "<h4 class='info'>" + file.name + "</h4>" + "<p class='state'>等待上传...</p>"
				+ "</div>");
	});

	uploader.onUploadProgress = function(file, percentage) {
		console.log(percentage);
		var $li = $('#' + file.id), $percent = $li.find('.progress span');

		$percent.css('width', percentage * 100 + '%');
	};

	uploader.on("uploadSuccess", function(file, response) {
		$("#filepath").val(response.datas);
		$("#" + file.id).find("p.state").text("已上传");
	});

	uploader.on("uploadError", function(file, reason) {
		$("#" + file.id).find("p.state").text("上传出错");
		// 多个文件
		var fileArray = uploader.getFiles();
		for (var i = 0; i < fileArray.length; i++) {
			uploader.cancelFile(fileArray[i]);
			uploader.removeFile(fileArray[i], true);
		}
		uploader.reset();
		fileSize = 0;
		fileName = [];
		fileSizeOneByOne = [];
	});

	// 当validate不通过时，会以派送错误事件的形式通知调用者
	uploader.on("error", function() {
		uploader.reset();
		fileSize = 0;
		fileName = [];
		fileSizeOneByOne = [];
		alert("error");
	})

	/**
	 * 执行多文件上传
	 */
	$("#fileuploadBtn").on("click", function() {
		uploader.upload();
	})

});

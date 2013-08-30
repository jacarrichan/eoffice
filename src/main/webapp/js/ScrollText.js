function ScrollText(d, b, a, c) {
	this.Delay = 2;
	this.LineHeight = 286;
	this.Amount = 1;
	this.Direction = "up";
	this.Timeout = 1500;
	this.ScrollContent = this.$(d);
	this.ScrollContent.innerHTML += this.ScrollContent.innerHTML;
	if (a) {
		this.NextButton = this.$(a);
		this.NextButton.onclick = this.GetFunction(this, "Next");
		this.NextButton.onmouseover = this.GetFunction(this, "Stop");
		this.NextButton.onmouseout = this.GetFunction(this, "Start");
	}
	if (b) {
		this.PreviousButton = this.$(b);
		this.PreviousButton.onclick = this.GetFunction(this, "Previous");
		this.PreviousButton.onmouseover = this.GetFunction(this, "Stop");
		this.PreviousButton.onmouseout = this.GetFunction(this, "Start");
	}
	this.ScrollContent.onmouseover = this.GetFunction(this, "Stop");
	this.ScrollContent.onmouseout = this.GetFunction(this, "Start");
	if (c) {
		this.Start();
	}
}
ScrollText.prototype.$ = function(a) {
	return document.getElementById(a);
};
ScrollText.prototype.Previous = function() {
	clearTimeout(this.AutoScrollTimer);
	clearTimeout(this.ScrollTimer);
	this.Scroll("up");
};
ScrollText.prototype.Next = function() {
	clearTimeout(this.AutoScrollTimer);
	clearTimeout(this.ScrollTimer);
	this.Scroll("down");
};
ScrollText.prototype.Start = function() {
	clearTimeout(this.AutoScrollTimer);
	this.AutoScrollTimer = setTimeout(this.GetFunction(this, "AutoScroll"),
			this.Timeout);
};
ScrollText.prototype.Stop = function() {
	clearTimeout(this.ScrollTimer);
	clearTimeout(this.AutoScrollTimer);
};
ScrollText.prototype.AutoScroll = function() {
	if (this.Direction == "up") {
		if (parseInt(this.ScrollContent.scrollTop) > 0) {
			if (parseInt(this.ScrollContent.scrollTop) >= parseInt(this.ScrollContent.scrollHeight) / 2) {
				this.ScrollContent.scrollTop = 0;
			}
		}
		this.ScrollContent.scrollTop += this.Amount;
	} else {
		if (parseInt(this.ScrollContent.scrollTop) <= 0) {
			this.ScrollContent.scrollTop = parseInt(this.ScrollContent.scrollHeight) / 2;
		}
		this.ScrollContent.scrollTop -= this.Amount;
	}
	if (parseInt(this.ScrollContent.scrollTop) % this.LineHeight != 0) {
		this.ScrollTimer = setTimeout(this.GetFunction(this, "AutoScroll"),
				this.Delay);
	} else {
		this.AutoScrollTimer = setTimeout(this.GetFunction(this, "AutoScroll"),
				this.Timeout);
	}
};
ScrollText.prototype.Scroll = function(a) {
	if (a == "up") {
		if (this.ScrollContent.scrollTop == 0) {
			this.ScrollContent.scrollTop = parseInt(this.ScrollContent.scrollHeight) / 2;
		}
		this.ScrollContent.scrollTop -= this.Amount;
	} else {
		this.ScrollContent.scrollTop += this.Amount;
	}
	if (parseInt(this.ScrollContent.scrollTop) >= parseInt(this.ScrollContent.scrollHeight) / 2) {
		this.ScrollContent.scrollTop = 0;
	}
	if (parseInt(this.ScrollContent.scrollTop) % this.LineHeight != 0) {
		this.ScrollTimer = setTimeout(this.GetFunction(this, "Scroll", a),
				this.Delay);
	}
};
ScrollText.prototype.GetFunction = function(a, c, b) {
	return function() {
		a[c](b);
	};
};
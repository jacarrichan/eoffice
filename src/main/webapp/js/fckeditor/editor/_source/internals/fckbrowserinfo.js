var s=navigator.userAgent.toLowerCase();var FCKBrowserInfo={IsIE:
/*@cc_on!@*/
false,IsIE7:
/*@cc_on!@*/
false&&(parseInt(s.match(/msie (\d+)/)[1],10)>=7),IsIE6:
/*@cc_on!@*/
false&&(parseInt(s.match(/msie (\d+)/)[1],10)>=6),IsSafari:s.Contains(" applewebkit/"),IsOpera:!!window.opera,IsAIR:s.Contains(" adobeair/"),IsMac:s.Contains("macintosh")};(function(a){a.IsGecko=(navigator.product=="Gecko")&&!a.IsSafari&&!a.IsOpera;a.IsGeckoLike=(a.IsGecko||a.IsSafari||a.IsOpera);if(a.IsGecko){var b=s.match(/rv:(\d+\.\d+)/);var c=b&&parseFloat(b[1]);if(c){a.IsGecko10=(c<1.8);a.IsGecko19=(c>1.8);}}if(a.IsSafari){a.IsSafari3=(parseFloat(s.match(/ applewebkit\/(\d+)/)[1])<526);}})(FCKBrowserInfo);
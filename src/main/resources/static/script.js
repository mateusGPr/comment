function go() {
    const subject = document.getElementById("path")?.value
	window.location.href = window.location.href + subject;
	return false;
}
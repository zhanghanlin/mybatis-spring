$(function() {
	$('#signUpForm').submit(function(){
		var pwd = $('#password').val();
		var rePwd = $('#rePassword').val();
		if (pwd != rePwd) {
			return false;
		}
	});
});
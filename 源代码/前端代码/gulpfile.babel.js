var gulp = require('gulp');
var gulpLoadPlugins  = require('gulp-load-plugins');
var cssmin = require('gulp-clean-css');
const plugins = gulpLoadPlugins();



gulp.task('copy', ['css_min'], function() {
  	gulp.src([
		'./static/dist/*'
    ]).pipe(gulp.dest('./static/dist/dist'));

    gulp.src('./lib/**/*')
        .pipe(gulp.dest('./static/lib'))
});

gulp.task('clean', ['copy_index'], function() {
	gulp.src([
		'./static/dist/*'
    ]).pipe(plugins.clean());   
})

gulp.task('copy_index', function() {
	gulp.src([
		'./index.html'
    ])
	.pipe(plugins.replace('<script src="dist/build.js"></script>', ''))
    .pipe(gulp.dest('./static'));
})

gulp.task('get_zip', function() {
    return gulp.src('static/**/*')
        .pipe(plugins.zip('static.zip'))
        .pipe(gulp.dest('./'));
});

gulp.task('css_min', function () {
    gulp.src('./static/dist/*.css')
        .pipe(cssmin())
        .pipe(gulp.dest('./static/dist'));
});
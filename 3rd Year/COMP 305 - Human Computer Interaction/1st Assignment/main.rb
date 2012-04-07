#!/usr/bin/ruby

require 'Qt4'
require 'dashboard'

a = Qt::Application.new(ARGV)

dashboard = Dashboard.new
dashboard.show

a.exec

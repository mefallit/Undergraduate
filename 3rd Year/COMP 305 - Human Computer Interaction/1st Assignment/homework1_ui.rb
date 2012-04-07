=begin
** Form generated from reading ui file 'homework1.ui'
**
** Created: Wed Oct 13 16:13:39 2010
**      by: Qt User Interface Compiler version 4.4.0
**
** WARNING! All changes made in this file will be lost when recompiling ui file!
=end

class Ui_Dialog
    attr_reader :pushButton
    attr_reader :label
    attr_reader :label_2

    def setupUi(dialog)
    if dialog.objectName.nil?
        dialog.objectName = "dialog"
    end
    dialog.resize(400, 300)
    @pushButton = Qt::PushButton.new(dialog)
    @pushButton.objectName = "pushButton"
    @pushButton.geometry = Qt::Rect.new(150, 170, 91, 31)
    @label = Qt::Label.new(dialog)
    @label.objectName = "label"
    @label.geometry = Qt::Rect.new(150, 60, 101, 31)
    @label_2 = Qt::Label.new(dialog)
    @label_2.objectName = "label_2"
    @label_2.geometry = Qt::Rect.new(130, 90, 141, 21)

    retranslateUi(dialog)

    Qt::MetaObject.connectSlotsByName(dialog)
    end # setupUi

    def setup_ui(dialog)
        setupUi(dialog)
    end

    def retranslateUi(dialog)
    dialog.windowTitle = Qt::Application.translate("Dialog", "Dialog", nil, Qt::Application::UnicodeUTF8)
    @pushButton.text = Qt::Application.translate("Dialog", "Sezgi Sensoz", nil, Qt::Application::UnicodeUTF8)
    @label.text = Qt::Application.translate("Dialog", "COMP 305 HW#1", nil, Qt::Application::UnicodeUTF8)
    @label_2.text = Qt::Application.translate("Dialog", "Sezgi Sensoz 10792020", nil, Qt::Application::UnicodeUTF8)
    end # retranslateUi

    def retranslate_ui(dialog)
        retranslateUi(dialog)
    end

end

module Ui
    class Dialog < Ui_Dialog
    end
end  # module Ui


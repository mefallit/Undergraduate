# We pull in the file containing the class which consists of generated code
require 'homework1_ui'

# We inherit from Qt:Dialog as it gives us access to User Interface
# functionality such as connecting slots and signals
class Dashboard < Qt::Dialog

  # We are then free to put our own code into this class without fear
  # of it being overwritten. Here we add a initialize function which
  # can be used to customise how the form looks on startup. The method
  # initialize() is a constructor in Ruby

  def initialize(parent = nil)

    # Widgets in Qt can be optionally be children of other widgets.
    # That's why we accept parent as a parameter

    # This super call causes the constructor of the base class (Qt::Widget)
    # to be called, shepherding on the parent argument

    super(parent)

    # The Dashboard class we are in holds presentation logic and exists
    # to 'manage' the dialog widget we created in Qt Designer earlier.
    # An instance of this dialog widget is created and stored in @ui variable

    @ui = Ui::Dialog.new

    # Calling setup_ui causes the dialog widget to be initialised with the
    # defaults you may have specified in Qt Designer. For example, it
    # - populates the 'First Line' and 'Second Line' items in the List Widget
    # - sets the drag drop mode to 'Internal'
    # - and much much more. Peer into the dashboard_ui.rb if you want to the
    #   full gory details

    @ui.setup_ui(self)

  end

end

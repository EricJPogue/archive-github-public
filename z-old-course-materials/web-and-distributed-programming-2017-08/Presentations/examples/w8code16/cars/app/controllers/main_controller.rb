class MainController < ApplicationController
# welcome method - fetches values for the initial view
  def welcome
    @num_cars = Corvette.count
  end

# result method - fetches values for the result view
  def result
    @year1 = params[:year1].to_i
    @year2 = params[:year2].to_i
    @body = params[:body]
    @selected_cars = Corvette.where(["year >= ? and year <= ? and
                     body_style = ?", @year1, @year2, @body]).all
  end

end

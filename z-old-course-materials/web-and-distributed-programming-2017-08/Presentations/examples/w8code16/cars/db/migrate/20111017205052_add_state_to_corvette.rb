class AddStateToCorvette < ActiveRecord::Migration
  def change
    add_column :corvettes, :state, :string
  end
end

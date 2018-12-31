class CorvettesController < ApplicationController
  # GET /corvettes
  # GET /corvettes.json
  def index
    @corvettes = Corvette.all

    respond_to do |format|
      format.html # index.html.erb
      format.json { render json: @corvettes }
    end
  end

  # GET /corvettes/1
  # GET /corvettes/1.json
  def show
    @corvette = Corvette.find(params[:id])

    respond_to do |format|
      format.html # show.html.erb
      format.json { render json: @corvette }
    end
  end

  # GET /corvettes/new
  # GET /corvettes/new.json
  def new
    @corvette = Corvette.new

    respond_to do |format|
      format.html # new.html.erb
      format.json { render json: @corvette }
    end
  end

  # GET /corvettes/1/edit
  def edit
    @corvette = Corvette.find(params[:id])
  end

  # POST /corvettes
  # POST /corvettes.json
  def create
    @corvette = Corvette.new(params[:corvette])

    respond_to do |format|
      if @corvette.save
        format.html { redirect_to @corvette, notice: 'Corvette was successfully created.' }
        format.json { render json: @corvette, status: :created, location: @corvette }
      else
        format.html { render action: "new" }
        format.json { render json: @corvette.errors, status: :unprocessable_entity }
      end
    end
  end

  # PUT /corvettes/1
  # PUT /corvettes/1.json
  def update
    @corvette = Corvette.find(params[:id])

    respond_to do |format|
      if @corvette.update_attributes(params[:corvette])
        format.html { redirect_to @corvette, notice: 'Corvette was successfully updated.' }
        format.json { head :ok }
      else
        format.html { render action: "edit" }
        format.json { render json: @corvette.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /corvettes/1
  # DELETE /corvettes/1.json
  def destroy
    @corvette = Corvette.find(params[:id])
    @corvette.destroy

    respond_to do |format|
      format.html { redirect_to corvettes_url }
      format.json { head :ok }
    end
  end
end

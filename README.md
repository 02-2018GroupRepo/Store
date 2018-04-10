# Microservice Project
The class has been separated into 9 teams: one store, three vendors, three suppliers, one purchase & resupply simulator and one financial analysis group. The first seven each have four members and are expected to use this project as a starting point, the last two consist of one pair each and may use this project or use s different approach altogether.

## bootcamp.Store
This group has been tasked to create a store that is kept stocked by making purchases from the three vendors.  Purchases will be made randomly by the customer simulator based on the retail price that they set. The store will purchase merchandise via orders based on the wholesale price; receive stock and invoices from the vendor and pay invoices directly to each vendor.

## Vendors
This group has been tasked to provide merchandise for sale to the store that it has purchased from the three vendors. The customer will purchase merchandise from the vendors via orders based on the retail price they set and pay invoices submitted by the vendor based on the wholesale price.  The vendors will purchase merchandise from the suppliers via orders; receive stock and invoices from the supplier and pay invoices directly to each supplier.

## Suppliers
This group has been tasked to provide merchandise for sale to the vendors that it has received from the resupply simulator. The vendors will purchase merchandise via orders based on the retail price they set and pay invoices submitted by the supplier based on the wholesale price. Suppliers have no control over the merchandise that it receives or the wholesale price that the resupply sets and must pay all invoices based on the merchandise received.  

## Customer & Resupply Simulator
This group has been tasked to provide a simulator to randomly purchase items from the store via orders and randomly send restock merchandise to each supplier.  While they receive invoice payment from the suppliers they have an infinite supply of money and do not need to track purchase and sale, just that the invoices are paid by the supplier before a restock is made.
They must purchase merchandise from the store at the price charged, but may change the restock **wholesale** price +/- 10% on each produc in an order sent to a vendor.

## Financial Analysis
This group has been tasked with setting the stock price for the store, vendors and suppliers via a proprietary method they create. They will provide the teams with the services they must expose with the information they require and the services they will provide for each team to report information.

## Project Guidelines
Each team will start with the following state: 
### bootcamp.Store
* Operating cash: $5750
* Inventory:      None
* Stock Price:    $47.50

### Vendors & Suppliers 
* Operating cash: $5000
* Inventory:       Randomly assigned
* Stock Price:     $50.00

## All teams
* Should follow proper Agile / XP priciples and practices
* All financial analysis tasks must take priority in the backlog, this doesn't mean that they are the only stories worked on
  but at least one pair should be working on these stories, if they are present.
* The winner will be determined by the financial analysis final stock price after a 3 hour simulation.
* May the odds be forever in your favor



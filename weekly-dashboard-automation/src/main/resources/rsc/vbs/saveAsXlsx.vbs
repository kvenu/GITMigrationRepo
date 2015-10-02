Dim objXL
Set objXL = CreateObject("Excel.Application")
With objXL
    .Workbooks.Open ("weekly_dashboard_macrobook.xlsb")
    .Application.Run "saveAsXlsx", "Mini_Dashboard.xls", "Mini_Dashboard.xlsx"
    .Application.Quit
End With
Set objXL = Nothing
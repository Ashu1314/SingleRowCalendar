package com.michalsvec.singlerowcalendar

import java.util.*

/**
 * Observer for events from SingleRowCalendar
 * @author Michal Švec
 * @since v1.0.0
 */

interface CalendarChangesObserver {

    /**
     * Called when year and month changed
     * @param monthNumber number of month from the changed date
     * @param monthName name of month from the changed date
     * @param year from the changed date
     * @param date changed date
     */
    fun whenMonthAndYearChanged(monthNumber: String, monthName: String, year: String, date: Date) {}

    /**
     * Called when selection changed
     * @param isSelected returns true if item in the SingleRowCalendar is selected else returns false
     * @param position of specific view in the SingleRowCalendar
     * @param date value of SingleRowCalendar item where selection changed
     */
    fun whenSelectionChanged(isSelected: Boolean, position: Int, date: Date) {}

    /**
     * Called when SingleRowCalendar scrolled
     * @param dx x-axis position of SingleRowCalendar
     * @param dy y-axis position of SingleRowCalendar
     */
    fun whenCalendarScrolled(dx: Int, dy: Int) {}

    /**
     * Called when selection is restored
     */
    fun whenSelectionRestored() {}

    /**
     * Called when selection is refreshed
     */
    fun whenSelectionRefreshed() {}
}
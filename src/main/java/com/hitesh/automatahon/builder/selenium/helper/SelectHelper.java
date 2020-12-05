package com.hitesh.automatahon.builder.selenium.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class SelectHelper {
    private static final Logger logger = LogManager.getLogger(SelectHelper.class.getName());

    public SelectHelper() {
        // To Do
    }

    /**
     * To get Object Of MultiSelect class
     *
     * @param element {@link WebElement}
     * @return {@link MultiSelect}
     */
    public MultiSelect multiSelect(WebElement element) {
        return new MultiSelect(element);
    }

    /**
     * Select all options that display text matching the argument.
     *
     * @param element {@link WebElement} at where to select
     * @param text    Text to select from options
     */
    public void selectByText(WebElement element, String text) {
        try {
            // Get object of select element
            new Select(element)
                    .selectByVisibleText(text);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    /**
     * Select all options that have a value matching the argument.
     *
     * @param element {@link WebElement} at where to select
     * @param value   Value to select from Options
     */
    public void selectByValue(WebElement element, String value) {
        try {
            // Get object of select element
            new Select(element)
                    .selectByValue(value);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    /**
     * Select the option at the given index.
     *
     * @param element {@link WebElement} at where to select
     * @param index   option at specific index to select
     */
    public void selectByIndex(WebElement element, int index) {
        try {
            // Get object of select element
            new Select(element)
                    .selectByIndex(index);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    /**
     * To get list of all options as {@link WebElement}
     *
     * @param element {@link WebElement} from which to getOptions
     * @return All options belonging to this select tag
     */
    public List<WebElement> getOptions(WebElement element) {
        List<WebElement> options = new ArrayList<>();
        try {
            options = new Select(element)
                    .getOptions();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return options;
    }


    public static class MultiSelect {
        private final Select selElement;

        public MultiSelect(WebElement selectElement) {
            this.selElement = new Select(selectElement);
        }

        /**
         * To check whether the Select element is of type multiple select or not
         *
         * @return true if multi select, false otherwise
         */
        private boolean isMultiSelectControl() {
            try {
                return selElement.isMultiple();
            } catch (Exception ex) {
                logger.error(ex.getMessage(), ex);
                return false;
            }
        }

        /**
         * Clear all selected entries.
         *
         * @return instance of {@link MultiSelect}
         */
        public MultiSelect deselectAll() {
            try {
                if (isMultiSelectControl()) {
                    selElement.deselectAll();
                } else {
                    throw new IllegalArgumentException("Cannot perform <deselectAll> since the control is not of type Multi Select.");
                }
            } catch (Exception ex) {
                logger.error(ex.getMessage(), ex);
            }
            return this;
        }

        /**
         * Deselect the option at the given index.
         *
         * @param index The option at this index will be deselected
         * @return instance of {@link MultiSelect}
         */
        public MultiSelect deselectByIndex(int index) {
            try {
                if (isMultiSelectControl()) {
                    selElement.deselectByIndex(index);
                } else {
                    throw new IllegalArgumentException("Cannot perform <deselectByIndex> since the control is not of type Multi Select.");
                }
            } catch (Exception ex) {
                logger.error(ex.getMessage(), ex);
            }
            return this;
        }

        /**
         * Deselect all options that have a value matching the argument.
         * That is,
         * when given "foo" this would deselect an option like:
         * <option value="foo">Bar</option>
         *
         * @param value The value to match against
         * @return instance of {@link MultiSelect}
         */
        public MultiSelect deselectByValue(String value) {
            try {
                if (isMultiSelectControl()) {
                    selElement.deselectByValue(value);
                } else {
                    throw new IllegalArgumentException("Cannot perform <deselectByValue> since the control is not of type Multi Select.");
                }
            } catch (Exception ex) {
                logger.error(ex.getMessage(), ex);
            }
            return this;
        }

        /**
         * Deselect all options that display text matching the argument.
         * That is, when given "Bar" this would deselect an option like:
         * <option value="foo">Bar</option>
         *
         * @param text The visible text to match against
         * @return instance of {@link MultiSelect}
         */
        public MultiSelect deselectByText(String text) {
            try {
                if (isMultiSelectControl()) {
                    selElement.deselectByVisibleText(text);
                } else {
                    throw new IllegalArgumentException("Cannot perform <deselectByText> since the control is not of type Multi Select.");
                }
            } catch (Exception ex) {
                logger.error(ex.getMessage(), ex);
            }
            return this;
        }

        /**
         * Select all options that display text matching the argument.
         *
         * @param optionsToSelect list of comma separated options to select
         */
        public void selectByText(String... optionsToSelect) {
            try {
                if (isMultiSelectControl()) {
                    if (optionsToSelect.length > 0) {
                        for (String option : optionsToSelect) {
                            selElement.selectByVisibleText(option);
                        }
                    } else {
                        logger.warn("No Options provided to select");
                    }
                } else {
                    throw new IllegalArgumentException("Cannot perform <selectByVisibleText> since the control is not of type Multi Select.");
                }
            } catch (Exception ex) {
                logger.error(ex.getMessage(), ex);
            }
        }

        /**
         * Select all options that have a value matching the argument.
         *
         * @param optionsToSelect list of comma separated values to select
         */
        public void selectByValue(String... optionsToSelect) {
            try {
                if (isMultiSelectControl()) {
                    if (optionsToSelect.length > 0) {
                        for (String value : optionsToSelect) {
                            selElement.selectByValue(value);
                        }
                    } else {
                        logger.warn("No Options provided to select");
                    }
                } else {
                    throw new IllegalArgumentException("Cannot perform <selectByValue> since the control is not of type Multi Select.");
                }
            } catch (Exception ex) {
                logger.error(ex.getMessage(), ex);
            }
        }

        /**
         * Select the option at the given index.
         *
         * @param optionsToSelect list of comma separated index to select
         */
        public void selectByIndex(int... optionsToSelect) {
            try {
                if (isMultiSelectControl()) {
                    if (optionsToSelect.length > 0) {
                        for (int index : optionsToSelect) {
                            selElement.selectByIndex(index);
                        }
                    } else {
                        logger.warn("No Options provided to select");
                    }
                } else {
                    throw new IllegalArgumentException("Cannot perform <selectByIndex> since the control is not of type Multi Select.");
                }
            } catch (Exception ex) {
                logger.error(ex.getMessage(), ex);
            }
        }

        /**
         * Get list of all {@link WebElement} options selected
         *
         * @return All selected options belonging to this select tag
         */
        public List<WebElement> getSelectedOptions() {
            List<WebElement> selectedOptions = new ArrayList<>();
            try {
                if (isMultiSelectControl()) {
                    selectedOptions = selElement.getAllSelectedOptions();
                } else {
                    throw new IllegalArgumentException("Cannot perform <getAllSelectedOptions> since the control is not of type Multi Select.");
                }
            } catch (Exception ex) {
                logger.error(ex.getMessage(), ex);
            }
            return selectedOptions;
        }

        /**
         * To get the first selected options as {@link WebElement}
         *
         * @return The first selected option in this select tag (or the currently selected option in a normal select)
         */
        public WebElement getFirstSelectedOption() {
            WebElement firstSelectedOption = null;
            try {
                if (isMultiSelectControl()) {
                    firstSelectedOption = selElement.getFirstSelectedOption();
                } else {
                    throw new IllegalArgumentException("Cannot perform <getFirstSelectedOption> since the control is not of type Multi Select.");
                }
            } catch (Exception ex) {
                logger.error(ex.getMessage(), ex);
            }
            return firstSelectedOption;
        }

        /**
         * @return All options belonging to this select tag
         */
        public List<WebElement> getOptions() {
            List<WebElement> allOptions = new ArrayList<>();
            try {
                if (isMultiSelectControl()) {
                    allOptions = selElement.getOptions();
                } else {
                    throw new IllegalArgumentException("Cannot perform <getOptions> since the control is not of type Multi Select.");
                }
            } catch (Exception ex) {
                logger.error(ex.getMessage(), ex);
            }
            return allOptions;
        }
    }
}
